package com.teamsparta.jangtrello.domain.user.dto

import java.time.LocalDateTime

data class UserResponse(
    val id:Long,
    val createdAt:LocalDateTime?,
    val modifiedAt: LocalDateTime?,
    var email:String,
    val nickName:String,
    val role : String,
)
