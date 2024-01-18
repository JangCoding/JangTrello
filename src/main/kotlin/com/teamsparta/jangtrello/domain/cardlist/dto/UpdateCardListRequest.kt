package com.teamsparta.jangtrello.domain.cardlist.dto

import org.springframework.security.core.userdetails.UserDetails

data class UpdateCardListRequest (
    var title : String,
    var details : UserDetails
)