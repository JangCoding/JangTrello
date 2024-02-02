package com.teamsparta.jangtrello.domain.user.model

import com.teamsparta.jangtrello.domain.BaseEntity
import jakarta.persistence.*

@Entity
//@EntityListeners(AuditingEntityListener::class) // Auditing 적용 위한 어노테이션
@Table(name = "users")
class User (
    // BaseEntity 상속받아 사용하기로
//    @CreatedDate
//    @Column(name = "created_at")
//    var createdAt: LocalDateTime? = null,
//
//    @LastModifiedDate
//    @Column(name = "modified_at")
//    var modifiedAt: LocalDateTime? = null,

    @Column(name = "password")
    var password : String,

    @Column(name = "email")
    var email:String,

    @Column(name = "nickname")
    var nickName:String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role:UserRole,

    ) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null
}


