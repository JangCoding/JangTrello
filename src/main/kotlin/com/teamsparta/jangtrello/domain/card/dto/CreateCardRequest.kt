package com.teamsparta.jangtrello.domain.card.dto

import jakarta.validation.constraints.Size

data class CreateCardRequest (
    @field:Size(min=1, max= 100, message = "BoardTitle must be between 1 and 100 characters")
    var title : String,
    @field:Size(min=1, max= 100, message = "BoardContents must be between 1 and 100 characters")
    var contents : String,
)