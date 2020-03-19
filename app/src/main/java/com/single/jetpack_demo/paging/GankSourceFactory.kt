package com.single.jetpack_demo.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hankkin.jetpack_note.data.bean.Gank
import com.single.jetpack_demo.net.ApiService
import com.single.jetpack_demo.utils.Injection

//创建gandatasource的factory类
class GankSourceFactory(private val api: ApiService = Injection.provideApi()) :
    DataSource.Factory<Int, Gank>() {
    val gankDataSource = MutableLiveData<GankDataSource>()
    override fun create(): DataSource<Int, Gank> {
        val gankSource = GankDataSource(api)
        Log.d("GankSourceFactory", "create")
        gankDataSource.postValue(gankSource)
        return gankSource
    }


}