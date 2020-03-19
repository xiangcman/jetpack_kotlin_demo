package com.single.jetpack_demo.db

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class CollectData(val pageList: LiveData<PagedList<Collect>>, val refresh: () -> Unit)