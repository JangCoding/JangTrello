package com.teamsparta.jangtrello.domain.card.dto

data class CardResponse(
    val id : Long, // 자동 생성
    val email : String,
    var date : String, // 자동 생성
    var nickName : String,
    var title : String,
    var status : String,
    var contents : String,
//    var comments : MutableList<Comment>
)
