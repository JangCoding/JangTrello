package com.teamsparta.jangtrello.domain.card.repository

import com.teamsparta.jangtrello.domain.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long>, CustomCardRepository {
    //fun findByCardListIdAndId(cardListId: Long, cardId: Long): Card?
    fun findAllByUserId(id : Long): List<Card>
    fun findByUserIdAndId(userId : Long, id : Long) : Card?

}