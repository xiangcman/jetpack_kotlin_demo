package com.single.jetpack_demo.direction

import android.os.Bundle
import androidx.navigation.NavDirections
import com.single.jetpack_demo.R

//私有的构造器
class ToRegistDirections private constructor() : NavDirections {
    //跳转时候的参数
    override fun getArguments(): Bundle {
        val bundle = Bundle()
        bundle.putString("action", "usercenter")
        return bundle
    }

    //跳转时候的id
    override fun getActionId(): Int {
        return R.id.action_loginFragment_to_registerFragment
    }

    companion object {
        fun getToRegistDirections(): ToRegistDirections {
            return ToRegistDirections()
        }
    }

}