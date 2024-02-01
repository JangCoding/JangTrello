package com.teamsparta.jangtrello.domain.comment.dto

import java.time.LocalDateTime

data class CommentResponse(
    val id : Long,
    val createdAt: LocalDateTime?,
    val modifiedAt: LocalDateTime?,
    var email : String,
    var nickName : String,
    var contents : String,
)
