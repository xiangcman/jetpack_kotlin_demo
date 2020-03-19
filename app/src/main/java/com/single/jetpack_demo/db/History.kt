package com.single.jetpack_demo.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hankkin.jetpack_note.data.bean.Gank
import java.util.*

@Entity
data class History(
    @PrimaryKey
    var _id: String = "",
    var who: String = "",
    var published: String = "",
    var url: String = "",
    var desc: String = "",
    var date: Date? = null
) {
    companion object {
        fun getThis(gank: Gank): History {
            val history = History()
            history.run {
                _id = gank._id
                who = gank.who
                published = gank.published
                url = gank.url
                desc = gank.desc
                date = Date()
            }

            return history
        }
    }
}