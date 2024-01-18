package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.user.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

interface UserService {
    fun signUp(request: SignUpRequest): UserResponse

    fun logIn(request: LogInRequest): LoginResponse
    fun updateUser(request: UpdateUserRequest, userId:Long ): UserResponse
}