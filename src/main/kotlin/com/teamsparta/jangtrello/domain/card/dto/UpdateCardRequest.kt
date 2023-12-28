package com.teamsparta.jangtrello.domain.card.dto


data class UpdateCardRequest(
    var title : String,
    var status : String,
    var contents : String,
    var userName : String,
)
