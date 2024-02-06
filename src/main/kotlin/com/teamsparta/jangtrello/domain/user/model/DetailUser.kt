package com.teamsparta.jangtrello.domain.user.model

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

// @QueryProjection 사용한 Projection
// 어노테이션 추가 후 Gradle > Tasks > other > compileKotlin 실행
// QDetailuser Qclass 생성
data class DetailUser @QueryProjection constructor(
    var createdAt: LocalDateTime,

    var email:String,

    var nickName:String,

    val role:UserRole,
)