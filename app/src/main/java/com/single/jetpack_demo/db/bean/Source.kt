package com.single.jetpack_demo.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Source(
    @PrimaryKey(autoGenerate = true) val id: Long, val color: String,
    val name: String
)