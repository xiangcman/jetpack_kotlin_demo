package com.single.jetpack_demo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.single.jetpack_demo.db.bean.History

@Dao
interface HistoryDao {
    @Query("select * from history order by date desc")
    fun findHistory(): LiveData<List<History>>

    @Query("delete from history")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(history: History): Long

}