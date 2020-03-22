package com.single.jetpack_demo.respository

import androidx.paging.toLiveData
import com.single.jetpack_demo.db.bean.Collect
import com.single.jetpack_demo.db.dao.CollectDao
import com.single.jetpack_demo.bean.CollectData

class CollectRespository private constructor(private val collectDao: CollectDao) {
    companion object {
        @Volatile
        private var instance: CollectRespository? = null

        //这是kotlin单例的写法
        fun getInstance(collectDao: CollectDao) =
            instance
                ?: synchronized(CollectRespository::class.java) {
                instance
                    ?: CollectRespository(
                        collectDao
                    ).also {
                    instance = it
                }
            }
    }

    fun getCollects(): CollectData {
        val findCollect = collectDao.findCollect()
        return CollectData(
            findCollect.toLiveData(pageSize = 10), { findCollect.create().invalidate() }
        )
    }

//    fun getCollects() = collectDao.findCollect().toLiveData(pageSize = 10)


    suspend fun delectCollect(collect: Collect) = collectDao.delete(collect)

}