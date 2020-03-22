package com.single.jetpack_demo.bean

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.single.jetpack_demo.db.bean.Collect

data class CollectData(val pageList: LiveData<PagedList<Collect>>, val refresh: () -> Unit)