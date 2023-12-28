package com.teamsparta.jangtrello.domain.user.dto

data class UpdateUserRequest(
    var pw : String,
    var email : String
)