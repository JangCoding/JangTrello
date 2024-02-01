package com.teamsparta.jangtrello.domain.card.dto

import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import java.time.LocalDateTime

data class CardResponse(
    val id : Long, // 자동 생성
    val email : String,
    var createdAt : LocalDateTime?, // 자동 생성
    var modifiedAt : LocalDateTime?, // 자동 생성
    var nickName : String,
    var title : String,
    var status : String,
    var contents : String,
    var comments : List<CommentResponse>
)
