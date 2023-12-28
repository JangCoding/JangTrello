package com.teamsparta.jangtrello.domain.cardlist.service

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

    override fun getCardListById(cardListId: Long): CardListResponse {
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
}