package com.single.jetpack_demo.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import org.jetbrains.annotations.NotNull

@Dao
interface CollectDao {
    @Query("select * from Collect order by date  COLLATE NOCASE DESC")
    fun findCollect(): DataSource.Factory<Int, Collect>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collect: Collect): Long

    @Delete
    suspend fun delete(collect: Collect): Int
}