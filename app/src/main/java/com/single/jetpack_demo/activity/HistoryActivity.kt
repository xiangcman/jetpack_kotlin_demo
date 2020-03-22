package com.single.jetpack_demo.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.single.jetpack_demo.BaseActivity
import com.single.jetpack_demo.R
import com.single.jetpack_demo.adapter.HistoryAdapter
import com.single.jetpack_demo.databinding.ActivityHistoryBinding
import com.single.jetpack_demo.db.bean.History
import com.single.jetpack_demo.utils.setupToolBar
import com.single.jetpack_demo.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.toolbar


class HistoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityHistoryBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_history
        ) as ActivityHistoryBinding

        val historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        val datas = mutableListOf<History>()
        val adapter = HistoryAdapter(this, datas)
        recycler_view.adapter = adapter
        initToolBar()
        historyViewModel.getAllHistory().observe(this, Observer {
            datas.clear()
            datas.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun initToolBar() {
        setupToolBar(toolbar, {
            setDisplayHomeAsUpEnabled(true)
            title = "浏览记录"
        }, {
            finish()
        })
    }
}