package com.single.jetpack_demo.respository

import androidx.lifecycle.LiveData
import androidx.paging.toLiveData
import com.single.jetpack_demo.db.*

class HistoryRespository private constructor(private val historyDao: HistoryDao) {
    companion object {
        @Volatile
        private var instance: HistoryRespository? = null

        //这是kotlin单例的写法
        fun getInstance(historyDao: HistoryDao) =
            instance
                ?: synchronized(HistoryRespository::class.java) {
                    instance
                        ?: HistoryRespository(
                            historyDao
                        ).also {
                            instance = it
                        }
                }
    }

    fun getAllHistory(): LiveData<List<History>> {
        return historyDao.findHistory()
    }

}