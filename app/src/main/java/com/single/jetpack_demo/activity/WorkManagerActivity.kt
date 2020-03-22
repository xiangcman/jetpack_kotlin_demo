package com.single.jetpack_demo.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.single.jetpack_demo.BaseActivity
import com.single.jetpack_demo.R
import com.single.jetpack_demo.databinding.ActivityWorkmanagerBinding
import com.single.jetpack_demo.databinding.ItemFlowBinding
import com.single.jetpack_demo.viewmodel.WorkManagerViewModel
import kotlinx.android.synthetic.main.activity_workmanager.*

class WorkManagerActivity : BaseActivity() {
    val TAG = WorkManagerActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityWorkmanagerBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_workmanager
            ) as ActivityWorkmanagerBinding
        val workManagerViewModel = ViewModelProviders.of(this).get(WorkManagerViewModel::class.java)
        workManagerViewModel.allDatas.observe(this, Observer {
            Log.d(TAG, "size:${it.size}")
            it.forEach {
                val dataBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(this),
                    R.layout.item_flow,
                    flow_layout,
                    false
                ) as ItemFlowBinding
                Log.d(TAG, "source:$it")
                dataBinding.source = it
                flow_layout.addView(dataBinding.root)
            }
        })

    }
}