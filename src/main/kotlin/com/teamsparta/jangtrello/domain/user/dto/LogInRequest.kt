package com.teamsparta.jangtrello.domain.user.dto

import jakarta.validation.constraints.Email

data class LogInRequest(
    val email:String,
    var password:String,
)
