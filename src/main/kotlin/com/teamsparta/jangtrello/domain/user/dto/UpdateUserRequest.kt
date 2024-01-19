package com.teamsparta.jangtrello.domain.user.dto

data class UpdateUserRequest(
    var password : String,
    var nickname : String,
)