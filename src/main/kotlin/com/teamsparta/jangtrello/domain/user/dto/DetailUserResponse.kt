package com.teamsparta.jangtrello.domain.user.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.teamsparta.jangtrello.domain.user.model.DetailUser
import java.time.LocalDateTime
data class DetailUserResponse(
    //val id:Long,
    val createdAt:LocalDateTime?,
    //val modifiedAt: LocalDateTime?,
    var email:String,
    val nickName:String,
    val role : String,
)

fun DetailUser.toResponse(): DetailUserResponse {
    return DetailUserResponse(
        createdAt = createdAt,
        email = email,
        nickName = nickName,
        role = role.name
    )
}

