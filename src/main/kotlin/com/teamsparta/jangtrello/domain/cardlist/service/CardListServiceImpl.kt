package com.teamsparta.jangtrello.domain.cardlist.service

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.cardlist.dto.CardListResponse
import com.teamsparta.jangtrello.domain.cardlist.dto.CreateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.dto.UpdateCardListRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CardListServiceImpl : CardListService{
    override fun getCardLists(): List<CardListResponse> {
        TODO("Not yet implemented")
    }

    override fun getCardList(cardListId: Long): CardListResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun createCardList(request: CreateCardListRequest): CardListResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateCardList(cardListId: Long, request: UpdateCardListRequest): CardListResponse {
        TODO("Not yet implemented")
    }
    @Transactional
    override fun deleteCardList(cardListId: Long) {
        TODO("Not yet implemented")
    }



    override fun getCards(cardListId: Long): List<CardResponse> {
        TODO("Not yet implemented")
    }

    override fun getCard(cardListId: Long, cardId: Long): CardResponse {
        TODO("Not yet implemented")
    }

    override fun createCard(cardListId: Long, request: CreateCardRequest): CardResponse {
        TODO("Not yet implemented")
    }

    override fun updateCard(cardListId: Long, cardId: Long, request: UpdateCardRequest): CardResponse {
        TODO("Not yet implemented")
    }

    override fun deleteCard(cardListId: Long, cardId: Long) {
        TODO("Not yet implemented")
    }
}