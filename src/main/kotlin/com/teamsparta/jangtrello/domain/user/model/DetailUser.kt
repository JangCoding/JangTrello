package com.teamsparta.jangtrello.domain.user.model

import java.time.LocalDateTime

data class DetailUser(
    var createdAt: LocalDateTime,

    var email:String,

    var nickName:String,

    val role:UserRole,
)