package com.single.jetpack_demo.worker

import android.content.Context
import android.util.JsonReader
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.single.jetpack_demo.db.AppDataBase
import com.single.jetpack_demo.db.bean.Source
import kotlinx.coroutines.coroutineScope

//负责向数据库插入数据

class SimpleWorker(val context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork() = coroutineScope {
        //处理worker的逻辑
        try {
            applicationContext.assets.open("source.json").use { inputStream ->
                inputStream.reader().use { reader ->
                    //插入到数据库，后面要使用该数据
                    val plantType = object : TypeToken<List<Source>>() {}.type
                    val plantList: List<Source> = Gson().fromJson(reader, plantType)
                    val database = AppDataBase.getInstance(applicationContext)
                    database.provideSourceDao().insertSource(plantList)
                    Log.d("SimpleWorker", "操作成功")
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }

    }

}


