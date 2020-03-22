package com.single.jetpack_demo.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.single.jetpack_demo.db.bean.History
import com.single.jetpack_demo.db.bean.Source
import com.single.jetpack_demo.db.bean.Collect
import com.single.jetpack_demo.db.dao.CollectDao
import com.single.jetpack_demo.db.dao.HistoryDao
import com.single.jetpack_demo.db.dao.SourceDao
import com.single.jetpack_demo.worker.SimpleWorker

@Database(
    entities = arrayOf(
        Collect::class, History::class,
        Source::class
    ), version = 2, exportSchema = false
)
@TypeConverters(value = arrayOf(Converters::class))
abstract class AppDataBase : RoomDatabase() {
    abstract fun provideCollectDao(): CollectDao
    abstract fun provideHistoryDao(): HistoryDao
    abstract fun provideSourceDao(): SourceDao

    companion object {
        private var instance: AppDataBase? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(AppDataBase::class.java) {
                instance ?: Room.databaseBuilder<AppDataBase>(
                    context.applicationContext,
                    AppDataBase::class.java, "AppDataBase"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        val oneTimeWorkRequestBuilder = OneTimeWorkRequestBuilder<SimpleWorker>()
                        val builder = Constraints.Builder()
                        builder.setRequiresDeviceIdle(true)
                        builder.setRequiredNetworkType(NetworkType.CONNECTED)  // 网络状态
                        builder.setRequiresBatteryNotLow(true)                 // 不在电量不足时执行
                        builder.setRequiresCharging(true)                      // 在充电时执行
                        builder.setRequiresStorageNotLow(true)                 // 不在存储容量不足时执行
                        builder.setRequiresDeviceIdle(true)                    // 在待机状态下执行，需要 API 23
                        oneTimeWorkRequestBuilder.setConstraints(builder.build())
                        val request = oneTimeWorkRequestBuilder.build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build().also {
                    instance = it
                }
            }
    }

}