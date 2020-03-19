package com.single.jetpack_demo.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.hankkin.jetpack_note.data.bean.Gank
import com.hankkin.jetpack_note.data.bean.GankResponse
import com.single.jetpack_demo.bean.NetWorkStatus
import com.single.jetpack_demo.net.ApiService
import com.single.jetpack_demo.utils.Injection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GankDataSource(private val api: ApiService) :
//第一个泛型指pageIndex类型，第二个参数是返回的数据类型
    PageKeyedDataSource<Int, Gank>() {


    private val TAG by lazy {
        GankDataSource::class.java.simpleName
    }

    init {
        Log.d(TAG, "重新初始化了")
    }


    //定义一个retry的lamda表达式
    private var retry: (() -> Unit)? = null

    val netWorkStatus = MutableLiveData<NetWorkStatus>()
    val refreshStatus = MutableLiveData<NetWorkStatus>()

    //因为retry在很多情况下都会发生，因此定义个方法供上层调用
    fun retryForAny() {
        val localRetry = retry
        retry = null
        localRetry?.run { invoke() }
    }

    var isRefresh = false


    //初始化时候的数据
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Gank>
    ) {
        Log.d(TAG, "loadInitial")
        //netWorkStatus正在请求数据
        //如果请求过数据，说明再调用就是刷新了
//        if (netWorkStatus.value is NetWorkStatus.SUCCESS) {
//            //走刷新了
//            refreshStatus.postValue(NetWorkStatus.LOADING())
//            isRefresh = true
//        } else {
//            netWorkStatus.postValue(NetWorkStatus.LOADING())
//            isRefresh = false
//        }

//        if (isRefresh) {
//            //走刷新了
//            refreshStatus.postValue(NetWorkStatus.LOADING())
////            isRefresh = true
//        } else {
        netWorkStatus.postValue(NetWorkStatus.LOADING())
//            isRefresh = false
//        }
        api.getGank(params.requestedLoadSize, 1)
            .enqueue(object : Callback<GankResponse> {
                override fun onFailure(call: Call<GankResponse>, t: Throwable) {
                    retry = {
                        loadInitial(params, callback)
                    }
//                    if (isRefresh) {
//                        refreshStatus.postValue(NetWorkStatus.ERROR())
//                    } else {
                    Log.d(TAG, "error:${t.message}")
                    netWorkStatus.postValue(NetWorkStatus.ERROR())
//                    }
                }

                override fun onResponse(
                    call: Call<GankResponse>,
                    response: Response<GankResponse>
                ) {
                    if (response.isSuccessful) {
//                        if (isRefresh) {
//                            refreshStatus.postValue(NetWorkStatus.SUCCESS())
//                        } else {
                        netWorkStatus.postValue(NetWorkStatus.SUCCESS())
//                        }
                        retry = null
                        Log.d(TAG, "body:${response.body()?.results?.size}")
                        callback.onResult(response.body()?.results ?: emptyList(), null, 2)
                    } else {
//                        if (isRefresh) {
//                            refreshStatus.postValue(NetWorkStatus.ERROR())
//                        } else {
                        netWorkStatus.postValue(NetWorkStatus.ERROR())
//                        }
                        retry = {
                            loadInitial(params, callback)
                        }
                    }
                }
            })
    }

    //加载下一页的方法
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Gank>) {
        //加载下一页
        api.getGank(params.requestedLoadSize, params.key)
            .enqueue(object : Callback<GankResponse> {
                override fun onFailure(call: Call<GankResponse>, t: Throwable) {
                    retry = {
                        loadAfter(params, callback)
                    }
//                    netWorkState.postValue(NetworkState.FAILED)
                }

                override fun onResponse(
                    call: Call<GankResponse>,
                    response: Response<GankResponse>
                ) {
                    if (response.isSuccessful) {
                        retry = null
                        callback.onResult(
                            response.body()?.results ?: emptyList(),
                            params.key + 1
                        )
//                        netWorkState.postValue(NetworkState.LOADED)
                    } else {
                        retry = {
                            loadAfter(params, callback)
                        }
//                        netWorkState.postValue(NetworkState.FAILED)
                    }
                }

            })
    }

    //刷新的方法
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Gank>) {
    }

}