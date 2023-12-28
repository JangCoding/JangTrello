package com.teamsparta.jangtrello.domain.card.service

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardServiceImpl : CardService {
    override fun getCards(): List<CardResponse> {
        TODO("Not yet implemented")
    }

    override fun getCardById(cardListId: Long): CardResponse {
        TODO("Not yet implemented")
    }
    @Transactional
    override fun createCard(request: CreateCardRequest): CardResponse {
        TODO("Not yet implemented")
    }
    @Transactional
    override fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse {
        TODO("Not yet implemented")
    }
    @Transactional
    override fun deleteCard(cardId: Long) {
        TODO("Not yet implemented")
    }
}