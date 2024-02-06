package com.teamsparta.jangtrello.domain.user.repository

import com.teamsparta.jangtrello.domain.user.model.DetailUser
import com.teamsparta.jangtrello.domain.user.model.SimpleUser
import com.teamsparta.jangtrello.domain.user.model.User
import com.teamsparta.jangtrello.domain.user.model.UserRole
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomUserRepository {
    fun findByPageableAndRole(pageable: Pageable, role: UserRole? ) : Page<User>

    // 유저 상세 정보
    fun findByIdDetailed(id:Long) : DetailUser?
    // 같은 닉네임 유저들 간단 정보 조회
    fun findByNickName(pageable: Pageable, nickName : String) : Page<SimpleUser>
}