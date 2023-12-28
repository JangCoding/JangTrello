package com.teamsparta.jangtrello.domain.user.dto

data class SignUpRequest(
    val id:Long,
    var email:String,
    var password:String,
    val name:String,
)
