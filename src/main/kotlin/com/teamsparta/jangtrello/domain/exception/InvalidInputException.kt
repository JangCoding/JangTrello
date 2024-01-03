package com.teamsparta.jangtrello.domain.exception

data class InvalidInputException(val t:Long, val c:Long) : RuntimeException(
    "Title must be between 1 and 200 characters(${t}), and contents must be between 1 and 1000 characters(${c})."
)
