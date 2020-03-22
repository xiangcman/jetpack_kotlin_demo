package com.single.jetpack_demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hankkin.jetpack_note.data.bean.Gank
import com.single.jetpack_demo.databinding.ItemPageDemoBinding
import com.single.jetpack_demo.db.bean.Collect
import com.single.jetpack_demo.db.bean.History
import com.single.jetpack_demo.viewmodel.PageViewModel
import com.single.jetpack_demo.web.CommonWebActivity

class PageDemoAdapter(val viewModel: PageViewModel) :
    PagedListAdapter<Gank, PageDemoAdapter.ViewHolder>(diffCallback) {

    companion object {
        //判断两个item是否是相同
        private val diffCallback = object : DiffUtil.ItemCallback<Gank>() {
            override fun areItemsTheSame(oldItem: Gank, newItem: Gank): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: Gank, newItem: Gank): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: ItemPageDemoBinding,
        private val viewModel: PageViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Gank?, click: (context: Context) -> Unit) {
            binding.run {
                onClickListener = View.OnClickListener {
                    click.invoke(it.context)
                }
                gank = item
                pageViewModel = viewModel
                collect = Collect.getThis(item!!)
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPageDemoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), viewModel
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item) {
            item?.run {
                //保存记录
                viewModel.addHistory(History.getThis(item!!))
                CommonWebActivity.loadUrl(it, url, desc)
            }

        }
    }
}