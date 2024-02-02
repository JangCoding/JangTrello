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

    // 할 일 카드 생성
    fun createCard(userPrincipal: UserPrincipal, request: CreateCardRequest)
            : CardResponse



    // 할 일 카드 전부 조회 ( 생성일, 상태 기준 정렬 )
    fun getCards(userPrincipal: UserPrincipal)
            : List<CardResponse>

    // 할 일 카드 생성일 기준 정렬 조회 ( 오름차순 / 내림차순 )
    fun getCardsSorted(userPrincipal: UserPrincipal, sortBy: String)
            : List<CardResponse>

    // 할일 카드 상태 기준 조회 ( 페이지 적용 , 기본값 : id 정렬 )
    fun getPagedCards(pageable: Pageable, status: String?, userPrincipal: UserPrincipal)
            : Page<CardResponse>?

    // 할 일 카드 단건 조회
    fun getCard(userPrincipal: UserPrincipal, cardId: Long)
            : CardResponse

    // 할 일 카드 제목 검색  // -> 내용 검색도 추가 해보기 ?
    fun searchCards(title: String)
            : List<CardResponse>

    // 할 일 카드 업데이트
    fun updateCard(userPrincipal: UserPrincipal, cardId: Long, request: UpdateCardRequest)
            : CardResponse

    // 할 일 카드 삭제
    fun deleteCard(userPrincipal: UserPrincipal, cardId: Long, password:String)
}