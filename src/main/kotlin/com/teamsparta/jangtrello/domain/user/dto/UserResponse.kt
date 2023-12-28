package com.teamsparta.jangtrello.domain.user.dto

data class UserResponse(
    val id:Long,
    // 비번은 보여주지 않는다. 해킹 위험 val pw:String,
    var email:String,
    val name:String,
)
