package com.teamsparta.jangtrello.domain.card.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class UpdateCardRequest(
    @field:Size(min=1, max= 100, message = "BoardTitle must be between 1 and 100 characters")
    var title : String,

    @field:NotNull(message = "Status must be not Null")
    var status : Boolean,

    @field:Size(min=1, max= 100, message = "BoardContents must be between 1 and 100 characters")
    var contents : String,
)
