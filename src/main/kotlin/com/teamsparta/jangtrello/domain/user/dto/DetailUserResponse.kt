package com.teamsparta.jangtrello.domain.user.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.teamsparta.jangtrello.domain.user.model.DetailUser
import java.time.LocalDateTime
//data class DetailUserResponse(
//    //val id:Long,
//    val createdAt:LocalDateTime?,
//    //val modifiedAt: LocalDateTime?,
//    var email:String,
//    val nickName:String,
//    val role : String,
//)


// Jackson 라이브러리가 해당 클래스를 직렬화 또는 역직렬화할 때
// 사용할 생성자를 찾을 수 있도록 함
data class DetailUserResponse @JsonCreator constructor(
    // Jackson 라이브러리가 해당 생성자의 매개변수와 JSON 키를 매핑할 수 있도록 필요한 작업/
    // 기본 생성자를 두는 것도 방법.
    @JsonProperty("createdAt") val createdAt: LocalDateTime,
    @JsonProperty("email") val email: String,
    @JsonProperty("nickName") val nickName: String,
    @JsonProperty("role") val role: String
)

fun DetailUser.toResponse(): DetailUserResponse {
    return DetailUserResponse(
        createdAt = createdAt,
        email = email,
        nickName = nickName,
        role = role.name
    )
}

