package com.teamsparta.jangtrello.domain.user.model

import com.teamsparta.jangtrello.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(name = "password")
    var password : String,

    @Column(name = "email")
    var email:String,

    @Column(name = "nickname")
    var nickName:String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role:UserRole,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        nickName = nickName,
        role = role.name
    )
}
