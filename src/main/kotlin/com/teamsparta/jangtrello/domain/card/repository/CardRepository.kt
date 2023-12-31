package com.teamsparta.jangtrello.domain.card.repository

import com.teamsparta.jangtrello.domain.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long> {
    //fun findByCardListIdAndId(cardListId: Long, cardId: Long): Card?
    fun findAllByCardListId(courseId: Long): List<Card>
}