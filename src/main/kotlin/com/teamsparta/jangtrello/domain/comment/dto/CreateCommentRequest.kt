package com.teamsparta.jangtrello.domain.comment.dto

data class CreateCommentRequest(
    var userName : String,
    var password : String,
    var contents : String,
)
