package com.single.jetpack_demo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.single.jetpack_demo.respository.PageRespository
import com.single.jetpack_demo.viewmodel.PageViewModel

class ViewModelFactory(private val pageRespository: PageRespository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PageViewModel(pageRespository) as T
    }
}