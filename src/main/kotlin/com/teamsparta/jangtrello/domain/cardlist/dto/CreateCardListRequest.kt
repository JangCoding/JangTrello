package com.teamsparta.jangtrello.domain.cardlist.dto

import org.springframework.security.core.userdetails.UserDetails

data class CreateCardListRequest(
    var title : String,
    var details : UserDetails
)
