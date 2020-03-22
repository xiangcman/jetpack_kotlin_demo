package com.single.jetpack_demo.viewmodel

import androidx.lifecycle.ViewModel
import com.single.jetpack_demo.ThisApp
import com.single.jetpack_demo.db.AppDataBase
import com.single.jetpack_demo.respository.WorkManagerRespository

class WorkManagerViewModel : ViewModel() {
    val workManagerRespository by lazy {
        WorkManagerRespository.getInstance(AppDataBase.getInstance(ThisApp.instance!!).provideSourceDao())
    }

    val allDatas = workManagerRespository.getAllSource()
}