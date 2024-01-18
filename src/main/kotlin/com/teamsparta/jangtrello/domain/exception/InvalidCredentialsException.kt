package com.teamsparta.jangtrello.domain.exception

data class InvalidCredentialsException(val target:String, val input:String) : RuntimeException(
    "The credential is invalid. $target does not match the provided value: $input"
)
