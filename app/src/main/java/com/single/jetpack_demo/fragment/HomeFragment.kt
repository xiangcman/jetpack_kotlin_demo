package com.single.jetpack_demo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.single.jetpack_demo.R
import com.single.jetpack_demo.adapter.PageDemoAdapter
import com.single.jetpack_demo.bean.NetWorkStatus
import com.single.jetpack_demo.databinding.ActivityPagingDemoBinding
import com.single.jetpack_demo.dialog.LoadingDialog
import com.single.jetpack_demo.activity.PageDemoActivity
import com.single.jetpack_demo.viewmodel.PageViewModel
import com.single.jetpack_demo.utils.Injection
import kotlinx.android.synthetic.main.activity_paging_demo.*

class HomeFragment : Fragment() {

    lateinit var loadingDialog: LoadingDialog
    private val viewModel: PageViewModel by viewModels {
        Injection.providePagingViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate(
            inflater,
            R.layout.activity_paging_demo,
            container,
            false
        ) as ActivityPagingDemoBinding
        binding.run {
            pageViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        val adapter = PageDemoAdapter(viewModel)
        binding.list.adapter = adapter
        viewModel.pages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.netWorkStatus.observe(viewLifecycleOwner, Observer {
            Log.d(PageDemoActivity.TAG, "netWorkStatus:$it")
            if (it is NetWorkStatus.LOADING) {
                content.isRefreshing = true
            } else {
                if (it is NetWorkStatus.ERROR) {
                    Toast.makeText(requireContext(), "加载失败", Toast.LENGTH_SHORT).show()
                }
                content.isRefreshing = false
            }
        })
        loadingDialog = LoadingDialog(requireContext())
        viewModel.collect.observe(viewLifecycleOwner, Observer {
            if (it) {
                loadingDialog.show()
                loadingDialog.setLoadingText("收藏中...")
            } else {
                loadingDialog.dismiss()
                Toast.makeText(requireContext(), "收藏成功", Toast.LENGTH_SHORT).show()
            }

        })
        return binding.root
    }

}