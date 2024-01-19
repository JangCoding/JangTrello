package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.user.dto.*
import com.teamsparta.jangtrello.infra.security.UserPrincipal

interface UserService {
    fun signUp(request: SignUpRequest): UserResponse

    fun logIn(request: LogInRequest): LoginResponse
    fun updateUser(userPrincipal: UserPrincipal, request: UpdateUserRequest): UserResponse
}