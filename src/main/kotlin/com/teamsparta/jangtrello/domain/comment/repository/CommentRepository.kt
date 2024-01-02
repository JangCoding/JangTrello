package com.teamsparta.jangtrello.domain.cardlist.repository

import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>{
    fun findAllByCardId(cardId: Long): List<Comment>
}