package com.single.jetpack_demo.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("select * from history order by date")
    fun findHistory(): LiveData<List<History>>

    @Query("delete from history")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(history: History): Long

}