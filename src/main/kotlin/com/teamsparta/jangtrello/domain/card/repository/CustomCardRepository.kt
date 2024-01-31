package com.teamsparta.jangtrello.domain.card.repository

import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomCardRepository {
    fun searchCardByTitle(title:String) : List<Card>
    fun findByPageableAndStatus(pageable: Pageable, status: CardStatus?) : Page<Card>
}