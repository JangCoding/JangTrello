package com.teamsparta.jangtrello.domain.user.repository

import com.teamsparta.jangtrello.domain.user.model.User
import com.teamsparta.jangtrello.domain.user.model.UserRole
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomUserRepository {
    fun findByPageableAndRole(pageable: Pageable, role: UserRole? ) : Page<User>
}