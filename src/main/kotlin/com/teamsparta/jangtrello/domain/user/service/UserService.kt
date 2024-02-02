package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.user.dto.*
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {
    fun signUp(request: SignUpRequest): UserResponse

    fun logIn(request: LogInRequest): LoginResponse

    fun getPagedUserList(pageable:Pageable,role:String?) : Page<UserResponse>
    fun updateUser(userPrincipal: UserPrincipal, request: UpdateUserRequest): UserResponse
}