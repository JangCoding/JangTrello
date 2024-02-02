package com.teamsparta.jangtrello.domain.user.service

import com.teamsparta.jangtrello.domain.user.dto.*
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserService {

    // 유저 회원 가입
    fun signUp(request: SignUpRequest): UserResponse

    // 유저 로그인
    fun logIn(request: LogInRequest): LoginResponse

    // 유저 리스트 조회 ( 페이지 적영, 역할 기준 )
    fun getPagedUserList(pageable:Pageable,role:String?) : Page<UserResponse>

    // 유저 업데이트
    fun updateUser(userPrincipal: UserPrincipal, request: UpdateUserRequest): UserResponse

    // 유저 삭제

}