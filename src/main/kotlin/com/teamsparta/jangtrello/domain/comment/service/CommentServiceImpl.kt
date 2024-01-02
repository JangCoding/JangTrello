package com.teamsparta.jangtrello.domain.comment.service

import com.example.courseregistration.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import com.teamsparta.jangtrello.domain.card.model.toResponse
import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.cardlist.model.toResponse
import com.teamsparta.jangtrello.domain.cardlist.repository.CommentRepository
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.jangtrello.domain.comment.model.Comment
import com.teamsparta.jangtrello.domain.comment.model.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val cardRepository: CardRepository,
    private val commentRepository: CommentRepository,
) : CommentService {
    override fun getComments(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    override fun getComment(commentId : Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        return comment.toResponse()
    }

    override fun createComment(cardId: Long, request: CreateCommentRequest): CommentResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        val comment = Comment(
            userName = request.userName,
            password = request.password,
            contents = request.contents,
            card = card
        )

        card.addComment(comment)

        commentRepository.save(comment)
        return comment.toResponse()
    }

    override fun updateComment(commentId : Long, request: UpdateCommentRequest): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        comment.userName = request.userName
        comment.password = request.password
        comment.contents = request.contents

        return commentRepository.save(comment).toResponse()
    }

    override fun deleteComment(cardId: Long, commentId: Long) {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        var comment = commentRepository.findByIdOrNull((commentId)) ?: throw ModelNotFoundException("Comment", commentId)
        card.removeCard(comment)
        cardRepository.save(card)
    }
}