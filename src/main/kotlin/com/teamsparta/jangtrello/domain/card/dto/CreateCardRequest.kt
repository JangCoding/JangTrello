package com.teamsparta.jangtrello.domain.card.dto

data class CreateCardRequest (
    var title : String,
    //var status : String, // 생성시 FALSE
    var contents : String,
    var userName : String,
)