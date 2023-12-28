package com.teamsparta.jangtrello.domain.cardlist.dto

data class CardListResponse(
    val id : Long,
    var title : String,
    val date : String,
    var count : Long,
    var userName : String,
)
