package com.single.jetpack_demo.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.single.jetpack_demo.R
import com.single.jetpack_demo.activity.AboutMeActivity
import com.single.jetpack_demo.activity.HistoryActivity
import com.single.jetpack_demo.activity.WorkManagerActivity
import com.single.jetpack_demo.databinding.FragmentMeBinding
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : Fragment() {
    lateinit var fragmentMeBinding: FragmentMeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMeBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_me, container, false
            )
        return fragmentMeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        browse_history.setOnClickListener {
            startActivity(Intent(requireContext(), HistoryActivity::class.java))
        }
        workmanager.setOnClickListener {
            startActivity(Intent(requireContext(), WorkManagerActivity::class.java))
        }
        about_me.setOnClickListener {
            startActivity(Intent(requireContext(), AboutMeActivity::class.java))
        }
    }
}
