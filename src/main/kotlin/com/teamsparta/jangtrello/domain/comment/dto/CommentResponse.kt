package com.teamsparta.jangtrello.domain.comment.dto

data class CommentResponse(
    val id : Long,
    var date : String,
    var email : String,
    var nickName : String,
    var contents : String,
)
