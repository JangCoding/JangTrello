package com.teamsparta.jangtrello.domain.comment.dto

data class CommentResponse(
    val id : Long,
    var email : String,
    var date : String,
    var contents : String,
)
