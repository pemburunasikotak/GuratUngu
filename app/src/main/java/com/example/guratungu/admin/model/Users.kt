package com.example.guratungu.admin.model

import com.example.guratungu.admin.listjoinadmin.ListChatModel

open class Users(
    var userId:String ="",
    var nama:String ="",
    var email: String ="",
    var password : String = "",
    var no_telp: String= "",
    //baru
    var listChatModel:ListChatModel=ListChatModel()

)