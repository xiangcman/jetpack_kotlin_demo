package com.single.jetpack_demo.respository

import androidx.lifecycle.LiveData
import com.single.jetpack_demo.db.bean.History
import com.single.jetpack_demo.db.bean.Source
import com.single.jetpack_demo.db.dao.HistoryDao
import com.single.jetpack_demo.db.dao.SourceDao

class WorkManagerRespository private constructor(private val sourceDao: SourceDao) {
    companion object {
        @Volatile
        private var instance: WorkManagerRespository? = null

        //这是kotlin单例的写法
        fun getInstance(sourceDao: SourceDao) =
            instance
                ?: synchronized(WorkManagerRespository::class.java) {
                    instance
                        ?: WorkManagerRespository(
                            sourceDao
                        ).also {
                            instance = it
                        }
                }
    }

    fun getAllSource(): LiveData<List<Source>> {
        return sourceDao.findAll()
    }

}