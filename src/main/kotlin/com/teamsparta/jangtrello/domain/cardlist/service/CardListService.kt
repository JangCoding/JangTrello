package com.teamsparta.jangtrello.domain.cardlist.service

import com.teamsparta.jangtrello.domain.cardlist.dto.CardListResponse
import com.teamsparta.jangtrello.domain.cardlist.dto.CreateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.dto.UpdateCardListRequest

interface CardListService {
    fun getCardLists() : List<CardListResponse>

    fun getCardListById(cardListId : Long) : CardListResponse

    fun createCardList(request : CreateCardListRequest) : CardListResponse

    fun updateCardList(cardListId : Long, request: UpdateCardListRequest ) : CardListResponse

    fun deleteCardList(cardListId : Long)
}