package com.teamsparta.jangtrello.domain.card.dto


data class UpdateCardRequest(
    var password : String,
    var title : String,
    var status : String,
    var contents : String,
)
