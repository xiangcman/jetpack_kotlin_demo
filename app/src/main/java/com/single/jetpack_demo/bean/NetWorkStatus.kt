package com.single.jetpack_demo.bean

sealed class NetWorkStatus {
    class LOADING : NetWorkStatus()
    class SUCCESS : NetWorkStatus()
    class EMPTY : NetWorkStatus()
    class ERROR : NetWorkStatus()
}