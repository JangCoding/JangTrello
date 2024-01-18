package com.teamsparta.jangtrello.domain.card.dto

import org.springframework.security.core.userdetails.UserDetails


data class UpdateCardRequest(
    var details : UserDetails,
    var title : String,
    var status : String,
    var contents : String,
)
