package com.teamsparta.jangtrello.domain.user.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @CreatedDate
    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(name = "modified_at")
    val modifiedAt: LocalDateTime? = null,

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


