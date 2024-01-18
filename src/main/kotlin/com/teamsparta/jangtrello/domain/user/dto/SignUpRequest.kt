package com.teamsparta.jangtrello.domain.user.dto

data class SignUpRequest(
    var email:String,
    var password:String,
    val role : String,
    val nickName:String,
)
