package com.single.jetpack_demo.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hankkin.jetpack_note.data.bean.Gank
import java.util.*

@Entity
data class Collect(
    @PrimaryKey
    var _id: String = "",
    var who: String = "",
    var published: String = "",
    var url: String = "",
    var desc: String = "",
    var date: Date? = null
) {
    companion object {
        fun getThis(gank: Gank): Collect {
            val collect = Collect()
            collect._id = gank._id
            collect.who = gank.who
            collect.published = gank.published
            collect.url = gank.url
            collect.desc = gank.desc
            collect.date = Date()
            return collect
        }
    }
}