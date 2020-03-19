package com.single.jetpack_demo.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.single.jetpack_demo.R
import com.single.jetpack_demo.adapter.PageDemoAdapter
import com.single.jetpack_demo.bean.NetWorkStatus
import com.single.jetpack_demo.databinding.ActivityPagingDemoBinding
import com.single.jetpack_demo.utils.Injection
import com.single.jetpack_demo.viewmodel.PageViewModel
import kotlinx.android.synthetic.main.activity_paging_demo.*

class PageDemoActivity : AppCompatActivity() {
    companion object {
        val TAG = "PageDemoActivity"
    }

    private val viewModel: PageViewModel by viewModels {
        Injection.providePagingViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_paging_demo
        ) as ActivityPagingDemoBinding
        binding.run {
            pageViewModel = viewModel
            lifecycleOwner = this@PageDemoActivity
        }
        val adapter = PageDemoAdapter(viewModel)
        binding.list.adapter = adapter
        viewModel.pages.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.netWorkStatus.observe(this, Observer {
            Log.d(TAG, "netWorkStatus:$it")
            if (it is NetWorkStatus.LOADING) {
                content.isRefreshing = true
            } else {
                if (it is NetWorkStatus.SUCCESS) {
                    content.isRefreshing = false
                }
            }
        })
    }
}