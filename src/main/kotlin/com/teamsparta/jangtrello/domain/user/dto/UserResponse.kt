package com.teamsparta.jangtrello.domain.user.dto

import com.teamsparta.jangtrello.domain.user.model.User
import java.time.LocalDateTime

data class UserResponse(
    val id:Long,
    val createdAt:LocalDateTime?,
    val modifiedAt: LocalDateTime?,
    var email:String,
    val nickName:String,
    val role : String,
)


fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        email = email,
        nickName = nickName,
        role = role.name
    )
}
