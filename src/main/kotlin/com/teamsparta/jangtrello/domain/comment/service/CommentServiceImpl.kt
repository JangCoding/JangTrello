package com.teamsparta.jangtrello.domain.comment.service

import com.example.courseregistration.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.cardlist.model.toResponse
import com.teamsparta.jangtrello.domain.cardlist.repository.CommentRepository
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.jangtrello.domain.comment.model.toResponse
import org.springframework.data.repository.findByIdOrNull

class CommentServiceImpl(
    private val cardRepository: CardRepository,
    private val commentRepository: CommentRepository,
) : CommentService {
    override fun getCommentLists(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    override fun getCommentList(cardId:Long, commentId : Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        return comment.toResponse()
    }

    override fun createComment(request: CreateCommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun updateComment(request: UpdateCommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun deleteComment(cardListId: Long) {
        TODO("Not yet implemented")
    }
}