package com.teamsparta.jangtrello.domain.user.dto

import com.teamsparta.jangtrello.domain.user.model.SimpleUser

data class SimpleUserResponse(
    var email:String,
    val nickName:String,
)

fun SimpleUser.toResponse(): SimpleUserResponse {
    return SimpleUserResponse(
        email = email,
        nickName = nickName,
    )
}