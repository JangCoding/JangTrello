package com.teamsparta.jangtrello.domain.user.dto

data class UserResponse(
    val id:Long,
    var email:String,
    val nickName:String,
    val role : String,
)
