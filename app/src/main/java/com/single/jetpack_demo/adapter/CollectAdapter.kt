package com.single.jetpack_demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.single.jetpack_demo.databinding.ItemCollectBinding
import com.single.jetpack_demo.db.Collect
import com.single.jetpack_demo.viewmodel.CollectViewModel
import com.single.jetpack_demo.web.CommonWebActivity

class CollectAdapter(val collectViewModels: CollectViewModel) :
    PagedListAdapter<Collect, CollectAdapter.ViewHolder>(diffCallback) {

    companion object {
        //判断两个item是否是相同
        private val diffCallback = object : DiffUtil.ItemCallback<Collect>() {
            override fun areItemsTheSame(oldItem: Collect, newItem: Collect): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: Collect, newItem: Collect): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: ItemCollectBinding,
        private val collectViewModels: CollectViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Collect?, click: (context: Context) -> Unit) {
            binding.run {
                onClickListener = View.OnClickListener {
                    click.invoke(it.context)
                }
                collect = item
                collectViewModel = collectViewModels
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCollectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), collectViewModels
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item) {
            item?.run {
                CommonWebActivity.loadUrl(it, url, desc)
            }

        }
    }
}