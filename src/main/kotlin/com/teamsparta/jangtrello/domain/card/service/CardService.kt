package com.teamsparta.jangtrello.domain.card.service

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface CardService {

    // 현재 유저 id 를 조회하여 카드 생성
    fun createCard(userPrincipal: UserPrincipal, request: CreateCardRequest)
            : CardResponse


    // 현재 유저 id 의 카드
    fun getCards(userPrincipal: UserPrincipal)
            : List<CardResponse>


    fun getCardsSorted(userPrincipal: UserPrincipal, sortBy: String)
            : List<CardResponse>

    fun getPagedCards(pageable: Pageable, status: String?, userPrincipal: UserPrincipal)
            : Page<CardResponse>?

    fun getCard(userPrincipal: UserPrincipal, cardId: Long)
            : CardResponse

    fun searchCards(title: String)
            : List<CardResponse>


    //    fun getCardsUserNamed(cardListId: Long, userName : String) : List<CardResponse>    {
//        return cardRepository.findAllByCardListId(cardListId).map { it.toResponse() }.sortedByDescending { it.date }.sortedBy { it.status }.filter{it.userName == userName }
//    }

    fun updateCard(userPrincipal: UserPrincipal, cardId: Long, request: UpdateCardRequest)
            : CardResponse

    fun deleteCard(userPrincipal: UserPrincipal, cardId: Long, password:String)
}