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
import com.single.jetpack_demo.adapter.CollectAdapter
import com.single.jetpack_demo.databinding.FragmentCollectBinding
import com.single.jetpack_demo.viewmodel.CollectViewModel
import com.single.jetpack_demo.utils.Injection
import kotlinx.android.synthetic.main.fragment_collect.*

class CollectFragment : Fragment() {

    private val viewModel: CollectViewModel by viewModels {
        Injection.provideCollectModelFactory(requireContext())
    }

    val TAG by lazy {
        CollectFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        val databing = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_collect,
            container,
            false
        ) as FragmentCollectBinding
        return databing.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pageDemoAdapter = CollectAdapter(viewModel)
        show_collect.adapter = pageDemoAdapter
        viewModel.datas.pageList.observe(viewLifecycleOwner, Observer {
            pageDemoAdapter.submitList(it)
        })
        viewModel.delectMutables.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "删除成功", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "删除失败", Toast.LENGTH_SHORT).show()
            }
        })
    }
}