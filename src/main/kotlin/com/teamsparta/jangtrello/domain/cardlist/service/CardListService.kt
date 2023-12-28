package com.teamsparta.jangtrello.domain.cardlist.service

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.cardlist.dto.CardListResponse
import com.teamsparta.jangtrello.domain.cardlist.dto.CreateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.dto.UpdateCardListRequest

interface CardListService {
    fun getCardLists() : List<CardListResponse>

    fun getCardList(cardListId : Long) : CardListResponse

    fun createCardList(request : CreateCardListRequest) : CardListResponse

    fun updateCardList(cardListId : Long, request: UpdateCardListRequest ) : CardListResponse

    fun deleteCardList(cardListId : Long)

    fun getCards(cardListId: Long) : List<CardResponse>

    fun getCard(cardListId: Long, cardId : Long) : CardResponse

    fun createCard(cardListId: Long, request : CreateCardRequest) : CardResponse

    fun updateCard(cardListId: Long, cardId : Long, request: UpdateCardRequest) : CardResponse

    fun deleteCard(cardListId: Long, cardId : Long)
}