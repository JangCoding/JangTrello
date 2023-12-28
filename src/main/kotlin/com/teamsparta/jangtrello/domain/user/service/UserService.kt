package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.user.dto.LogInRequest
import com.teamsparta.jangtrello.domain.user.dto.SignUpRequest
import com.teamsparta.jangtrello.domain.user.dto.UpdateUserRequest
import com.teamsparta.jangtrello.domain.user.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

interface UserService {
    fun signUp(
        @RequestBody request: SignUpRequest
    ): ResponseEntity<UserResponse>

    fun logIn(
        @RequestBody request: LogInRequest
    ): ResponseEntity<UserResponse>
    fun updateUser(
        @RequestBody request: UpdateUserRequest,
        @PathVariable userId:Long
    ): ResponseEntity<UserResponse>
}