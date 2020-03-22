package com.single.jetpack_demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.single.jetpack_demo.R
import com.single.jetpack_demo.databinding.ItemHistoryBinding
import com.single.jetpack_demo.db.bean.History

class HistoryAdapter(val context: Context, val datas: List<History>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_history,
            null,
            false
        )
    )


    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.itemHistoryBinding.run {
            history = datas.get(position)
            executePendingBindings()
        }
    }

    class ViewHolder(val itemHistoryBinding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(itemHistoryBinding.root) {

    }

}