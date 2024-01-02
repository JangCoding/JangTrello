package com.teamsparta.jangtrello.domain.exception

data class InvalidCredentialsException(val target:String, val input:String) : RuntimeException(
    "$target does not match the provided value: $input"
)
