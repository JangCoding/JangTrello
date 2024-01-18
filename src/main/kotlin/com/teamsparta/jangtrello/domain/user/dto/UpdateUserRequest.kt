package com.teamsparta.jangtrello.domain.user.dto

data class UpdateUserRequest(
    var email : String,
    var password : String,
)