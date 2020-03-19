package com.single.jetpack_demo.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.single.jetpack_demo.R
import com.single.jetpack_demo.databinding.FragmentLoginBinding
import com.single.jetpack_demo.direction.ToRegistDirections

class LoginFragment : Fragment() {
    companion object {
        val TAG = "LoginFragment"
    }

    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onClick = View.OnClickListener {
            //也可以定义一个NavDirections
            val toRegistDirections = ToRegistDirections.getToRegistDirections()
            Navigation.findNavController(activity!!, R.id.fragment_home)
                .navigate(toRegistDirections)
            //方式二也可以
//            val bundle = Bundle()
//            bundle.putString("action", "usercenter")
//            Navigation.findNavController(activity!!, R.id.fragment_home)
//                .navigate(R.id.action_loginFragment_to_registerFragment, bundle)
        }

    }
}