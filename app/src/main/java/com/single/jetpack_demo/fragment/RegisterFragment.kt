package com.single.jetpack_demo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.single.jetpack_demo.R
import com.single.jetpack_demo.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    companion object {
        val TAG = "RegisterFragment"
    }

    lateinit var fragmentRegisterBinding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentRegisterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return fragmentRegisterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val action = arguments?.getString("action")
        Log.d(TAG, "action:$action")
    }
}