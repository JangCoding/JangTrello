package com.teamsparta.jangtrello.domain.comment.dto

data class CommentResponse(
    val id : Long,
    var userName : String,
    var password : String,
    var contents : String,
    var date : String,
)
