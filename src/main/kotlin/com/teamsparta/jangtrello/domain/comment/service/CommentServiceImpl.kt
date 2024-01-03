package com.teamsparta.jangtrello.domain.comment.service

import com.teamsparta.jangtrello.domain.card.model.Card
import com.teamsparta.jangtrello.domain.card.model.CardStatus
import com.teamsparta.jangtrello.domain.card.model.toResponse
import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.cardlist.model.toResponse
import com.teamsparta.jangtrello.domain.cardlist.repository.CommentRepository
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.jangtrello.domain.comment.model.Comment
import com.teamsparta.jangtrello.domain.comment.model.toResponse
import com.teamsparta.jangtrello.domain.exception.InvalidCredentialsException
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val cardRepository: CardRepository,
    private val commentRepository: CommentRepository,
) : CommentService {
    override fun getComments(cardId: Long): List<CommentResponse> {
        return commentRepository.findAllByCardId(cardId).map { it.toResponse() }
    }

    override fun getComment(commentId: Long): CommentResponse {
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

    override fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentResponse {
        // 이름/비번 체크기능 추가 필요
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if (request.userName != comment.userName) {
            throw InvalidCredentialsException("UserName", request.userName)
        } else if (request.password != comment.password) {
            throw InvalidCredentialsException("Password", request.userName)
        } else {
            comment.userName = request.userName
            comment.password = request.password
            comment.contents = request.contents
        }
        return commentRepository.save(comment).toResponse()
    }

    override fun deleteComment(cardId: Long, commentId: Long, request: DeleteCommentRequest) {
        //InvalidCredentialsException
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        val comment =
            commentRepository.findByIdOrNull((commentId)) ?: throw ModelNotFoundException("Comment", commentId)

        if (request.userName != comment.userName) {
            throw InvalidCredentialsException("UserName", request.userName)
        } else if (request.password != comment.password) {
            throw InvalidCredentialsException("Password", request.userName)
        } else {
            // 예외가 발생하지 않은 경우에만 실행
            card.removeCard(comment)
            cardRepository.save(card)
        }
    }
}