package com.teamsparta.jangtrello.domain.card.service

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest

interface CardService {
    fun getCards() : List<CardResponse>

    fun getCardById(cardId : Long) : CardResponse

    fun createCard(request : CreateCardRequest) : CardResponse

    fun updateCard(cardId : Long, request: UpdateCardRequest) : CardResponse

    fun deleteCard(cardId : Long)
}