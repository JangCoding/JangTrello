package com.teamsparta.jangtrello.domain.card.dto

data class CreateCardRequest (
    var title : String,
    var status : Boolean,
    var contents : String,
    var userName : String,
)