package com.teamsparta.jangtrello.domain.user.repository

import com.teamsparta.jangtrello.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository  : JpaRepository<User, Long> {
}