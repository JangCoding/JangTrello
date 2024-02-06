package com.teamsparta.jangtrello.domain.user.model

// User Entity 의 몇몇 정보만 출력할 Projection 용 DTO
data class SimpleUser(
    var email:String, // 원본 Entity 필드와 이름, 순서 같아야함 ( contructor 방식으로 구현 시 )
    var nickName:String,
)
