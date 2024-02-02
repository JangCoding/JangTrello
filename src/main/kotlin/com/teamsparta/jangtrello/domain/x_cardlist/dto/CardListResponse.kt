package com.teamsparta.jangtrello.domain.x_cardlist.dto

data class CardListResponse(
    val id : Long,
    var title : String,
    val date : String,
    var count : Long,
    var userName : String,
)
