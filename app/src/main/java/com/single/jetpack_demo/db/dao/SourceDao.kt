package com.single.jetpack_demo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.single.jetpack_demo.db.bean.Source

@Dao
interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSource(list: List<Source>)

    @Query("select * from source")
    fun findAll(): LiveData<List<Source>>
}