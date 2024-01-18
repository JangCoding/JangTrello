package com.teamsparta.jangtrello.domain.card.dto

import org.springframework.security.core.userdetails.UserDetails

data class DeleteCardRequest (
    var details : UserDetails
)