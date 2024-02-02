package com.teamsparta.jangtrello.domain.card.repository

import com.teamsparta.jangtrello.domain.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long>, CustomCardRepository {
    //fun findByCardListIdAndId(cardListId: Long, cardId: Long): Card?

    // 유저 id 와 일치하는 모든 카드 가져오기
    fun findAllByUserId(id : Long): List<Card>

    // userId 와 cardID가 일치하는 항목 찾기
    fun findByUserIdAndId(userId : Long, cardId : Long) : Card?

}