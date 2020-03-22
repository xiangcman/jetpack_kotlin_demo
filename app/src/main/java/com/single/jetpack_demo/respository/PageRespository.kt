package com.single.jetpack_demo.respository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hankkin.jetpack_note.data.bean.Gank
import com.single.jetpack_demo.bean.GankData
import com.single.jetpack_demo.db.bean.Collect
import com.single.jetpack_demo.db.dao.CollectDao
import com.single.jetpack_demo.db.bean.History
import com.single.jetpack_demo.db.dao.HistoryDao
import com.single.jetpack_demo.paging.GankSourceFactory

class PageRespository private constructor(
    private val collectDao: CollectDao,
    private val historyDao: HistoryDao
) {

    companion object {
        val PAGE_SIZE = 10
        @Volatile
        private var instance: PageRespository? = null

        //这是kotlin单例的写法
        fun getInstance(collectDao: CollectDao, historyDao: HistoryDao) =
            instance
                ?: synchronized(PageRespository::class.java) {
                    instance
                        ?: PageRespository(
                            collectDao, historyDao
                        ).also {
                            instance = it
                        }
                }
    }

    fun getGank(): GankData<Gank> {
        //首先生成对应的sourceDataFactory对象
        val gankFactory = GankSourceFactory()
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(
                PAGE_SIZE * 2
            )
            .setEnablePlaceholders(false)
            .build()


        val pageList = LivePagedListBuilder<Int, Gank>(gankFactory, config).build()
        val refreshStatus = Transformations.switchMap(gankFactory.gankDataSource) {
            it.refreshStatus
        }

        val netWorkStatus = Transformations.switchMap(gankFactory.gankDataSource) {
            it.netWorkStatus
        }
        return GankData<Gank>(
            pageList = pageList,
            netWorkStatus = netWorkStatus,
            refreshStatus = refreshStatus,
            refresh = {
                val value = gankFactory.gankDataSource.value
                value?.isRefresh = true
                value?.invalidate()
            },
            retry = { gankFactory.gankDataSource.value?.retryForAny() }
        )
    }

    suspend fun addCollect(collect: Collect): Long {
        return collectDao.insert(collect)
    }

    suspend fun addHistory(history: History): Long {
        return historyDao.addHistory(history)
    }


}