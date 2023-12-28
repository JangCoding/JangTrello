package com.teamsparta.jangtrello.domain.card.dto

data class CardResponse(
    val id : Long, // 자동 생성
    var title : String,
    var status : String,
    var contents : String,
    var date : String, // 자동 생성
    var userName : String,
)
