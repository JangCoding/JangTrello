package com.teamsparta.jangtrello.domain.x_cardlist.service

import com.teamsparta.jangtrello.domain.x_cardlist.dto.CardListResponse
import com.teamsparta.jangtrello.domain.x_cardlist.dto.CreateCardListRequest
import com.teamsparta.jangtrello.domain.x_cardlist.dto.UpdateCardListRequest

interface CardListService {
    fun getCardLists() : List<CardListResponse>

    fun getCardList(cardListId : Long) : CardListResponse

    fun createCardList(request : CreateCardListRequest) : CardListResponse

    fun updateCardList(cardListId : Long, request: UpdateCardListRequest ) : CardListResponse

    fun deleteCardList(cardListId : Long)

}