package com.teamsparta.jangtrello.domain.user.repository

import com.teamsparta.jangtrello.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository  : JpaRepository<User, Long>, CustomUserRepository {

    fun existsByEmail(email: String): Boolean
    fun existsByNickName(nickname: String): Boolean
    fun findByEmail(email : String) : User? // 유저가 null일수도

}