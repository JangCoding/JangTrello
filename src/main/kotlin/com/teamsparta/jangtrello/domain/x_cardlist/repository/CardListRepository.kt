package com.teamsparta.jangtrello.domain.x_cardlist.repository

import com.teamsparta.jangtrello.domain.x_cardlist.model.CardList
import org.springframework.data.jpa.repository.JpaRepository

interface CardListRepository : JpaRepository<CardList, Long>{
    //fun findAllByUserId(userId: Long): List<CardList>
}