package com.teamsparta.jangtrello.domain.user.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import org.apache.commons.lang3.EnumUtils

enum class UserRole {
    USER,
    MANAGER;

    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(name: String?): CardStatus? =
            name?.let { EnumUtils.getEnumIgnoreCase(CardStatus::class.java, it.trim()) }
    }
}