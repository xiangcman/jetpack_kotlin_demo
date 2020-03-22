package com.single.jetpack_demo.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.single.jetpack_demo.db.bean.Collect

@Dao
interface CollectDao {
    @Query("select * from Collect order by date  COLLATE NOCASE DESC")
    fun findCollect(): DataSource.Factory<Int, Collect>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collect: Collect): Long

    @Delete
    suspend fun delete(collect: Collect): Int
}