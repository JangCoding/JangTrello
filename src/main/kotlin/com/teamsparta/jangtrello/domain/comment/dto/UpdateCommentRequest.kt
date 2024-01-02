package com.teamsparta.jangtrello.domain.comment.dto

data class UpdateCommentRequest (
    var userName : String,
    var password : String,
    var contents : String,
)
