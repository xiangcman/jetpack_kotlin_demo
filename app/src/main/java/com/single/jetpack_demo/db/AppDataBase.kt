package com.single.jetpack_demo.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Collect::class, History::class), version = 1, exportSchema = false)
@TypeConverters(value = arrayOf(Converters::class))
abstract class AppDataBase : RoomDatabase() {
    abstract fun provideCollectDao(): CollectDao
    abstract fun provideHistoryDao(): HistoryDao

    companion object {
        private var instance: AppDataBase? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(AppDataBase::class.java) {
                instance ?: Room.databaseBuilder<AppDataBase>(
                    context.applicationContext,
                    AppDataBase::class.java, "AppDataBase"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        fillInDb(context.applicationContext)
                    }
                }).build().also {
                    instance = it
                }
            }
    }

}