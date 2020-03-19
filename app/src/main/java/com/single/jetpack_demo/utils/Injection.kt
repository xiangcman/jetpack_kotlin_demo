package com.single.jetpack_demo.utils

import android.content.Context
import com.single.jetpack_demo.ThisApp
import com.single.jetpack_demo.db.AppDataBase
import com.single.jetpack_demo.factory.CollectViewModelFactory
import com.single.jetpack_demo.factory.ViewModelFactory
import com.single.jetpack_demo.net.ApiService
import com.single.jetpack_demo.respository.CollectRespository
import com.single.jetpack_demo.respository.HistoryRespository
import com.single.jetpack_demo.respository.PageRespository

//专门用来生成对象的类，相当于java里面的工厂类
object Injection {

    fun provideApi(): ApiService = ApiService.instance

    //page
    fun providePageRepository(context: Context): PageRespository =
        PageRespository.getInstance(
            AppDataBase.getInstance(context).provideCollectDao(),
            AppDataBase.getInstance(context).provideHistoryDao()
        )

    fun providePagingViewModelFactory(context: Context): ViewModelFactory {
        val repository = providePageRepository(context)
        return ViewModelFactory(repository)
    }


    //collect
    fun provideCollectRespository(context: Context): CollectRespository =
        CollectRespository.getInstance(AppDataBase.getInstance(context).provideCollectDao())

    fun provideCollectModelFactory(context: Context): CollectViewModelFactory {
        val repository = provideCollectRespository(context)
        return CollectViewModelFactory(repository)
    }

    //history
    fun provideHistoryRespository(): HistoryRespository {
        return HistoryRespository.getInstance(AppDataBase.getInstance(ThisApp.instance!!).provideHistoryDao())
    }

}