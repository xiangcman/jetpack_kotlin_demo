package com.single.jetpack_demo.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.single.jetpack_demo.BaseActivity
import com.single.jetpack_demo.R
import com.single.jetpack_demo.databinding.ActivityAboutMeBinding
import com.single.jetpack_demo.utils.setupToolBar
import com.single.jetpack_demo.web.CommonWebActivity
import kotlinx.android.synthetic.main.activity_about_me.*
import kotlinx.android.synthetic.main.activity_about_me.toolbar
import kotlinx.android.synthetic.main.activity_article_detail.*

class AboutMeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityAboutMeBinding>(this, R.layout.activity_about_me)
        initToolBar()
        juejin.setOnClickListener {
            CommonWebActivity.loadUrl(
                this,
                "https://juejin.im/user/5955ccc4f265da6c2f0a99b8/posts",
                "掘金"
            )
        }
        jianshu.setOnClickListener {
            CommonWebActivity.loadUrl(
                this,
                "https://www.jianshu.com/u/7b186b7247c1",
                "简书"
            )
        }
        github.setOnClickListener {
            CommonWebActivity.loadUrl(
                this,
                "https://github.com/xiangcman",
                "github"
            )
        }
    }

    private fun initToolBar() {
        setupToolBar(toolbar, {
            setDisplayHomeAsUpEnabled(true)
            title = "关于我"
        }, {
            finish()
        })
    }
}