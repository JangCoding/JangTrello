package com.teamsparta.jangtrello.domain.card.model

import com.fasterxml.jackson.annotation.JsonCreator
import org.apache.commons.lang3.EnumUtils

enum class CardStatus {
    FALSE,
    TRUE;

    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(name: String?): CardStatus? =
            name?.let { EnumUtils.getEnumIgnoreCase(CardStatus::class.java, it.trim()) }
    }
}
