package com.teamsparta.jangtrello.domain.card.dto

import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.model.toResponse
import java.time.LocalDateTime

data class CardResponse(
    val id : Long, // 자동 생성
    val email : String,
    var createdAt : LocalDateTime, // 자동 생성
    var nickName : String?,
    var title : String,
    var status : String,
    var contents : String,
    var comments : List<CommentResponse>
)
fun Card.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        email = email,
        createdAt = createdAt,
        nickName = nickName,
        title = title,
        status = status.name,
        contents = contents,
        comments = comments.map{ it.toResponse() }
    )
}