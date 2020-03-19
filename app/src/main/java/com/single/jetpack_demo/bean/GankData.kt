package com.single.jetpack_demo.bean

import androidx.lifecycle.LiveData
import androidx.paging.PagedList


class GankData<T>(
    val pageList: LiveData<PagedList<T>>,
    val netWorkStatus: LiveData<NetWorkStatus>,
    //这个livedata是给swiperefreshlayout用的，方便它控制状态
    val refreshStatus: LiveData<NetWorkStatus>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)