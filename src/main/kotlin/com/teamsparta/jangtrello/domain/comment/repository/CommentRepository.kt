package com.teamsparta.jangtrello.domain.comment.repository

import com.teamsparta.jangtrello.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>{
    fun findAllByUserIdAndCardId(userId:Long, cardId: Long): List<Comment>?
    fun findByUserIdAndId(userId:Long, commentId:Long) : Comment?
}