package com.teamsparta.jangtrello.domain.comment.service

import com.teamsparta.jangtrello.domain.card.repository.CardRepository
import com.teamsparta.jangtrello.domain.cardlist.repository.CommentRepository
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.jangtrello.domain.comment.model.Comment
import com.teamsparta.jangtrello.domain.exception.InvalidCredentialsException
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.domain.user.repository.UserRepository
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val userRepository: UserRepository,
    private val cardRepository: CardRepository,
    private val commentRepository: CommentRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun getComments(
        userPrincipal: UserPrincipal,
        cardId: Long
    ): List<CommentResponse> {
        return commentRepository.findAllByUserIdAndCardId(userPrincipal.id, cardId)
            ?.map { it.toResponse() }
            ?: throw ModelNotFoundException("Comments", cardId)
    }

    fun getComment(
        userPrincipal: UserPrincipal,
        commentId: Long
    ): CommentResponse {
        val comment = commentRepository.findByUserIdAndId(userPrincipal.id, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        return comment.toResponse()
    }

    fun createComment(
        userPrincipal: UserPrincipal,
        cardId: Long,
        request: CreateCommentRequest
    ): CommentResponse {
        val user = userRepository.findById(userPrincipal.id).orElse(null)
            ?: throw IllegalStateException("User not found with id: ${userPrincipal.id}")

        val card = cardRepository.findByIdOrNull(cardId)
            ?: throw ModelNotFoundException("Card", cardId)
        val comment = Comment(
            email = userPrincipal.email,
            contents = request.contents,
            user = user,
            nickName = user.nickName,
            card = card,
        )
        commentRepository.save(comment)
        return comment.toResponse()
    }

    fun updateComment(
        userPrincipal: UserPrincipal,
        commentId: Long,
        request: UpdateCommentRequest
    ): CommentResponse {
        val comment = commentRepository.findByUserIdAndId(userPrincipal.id, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        val user = userRepository.findById(userPrincipal.id).orElse(null)
            ?: throw IllegalStateException("User not found with id: ${userPrincipal.id}")

        if (!passwordEncoder.matches(request.password, user.password))
            throw InvalidCredentialsException("Password", request.password)

        comment.contents = request.contents

        return commentRepository.save(comment).toResponse()
    }

    fun deleteComment(
        userPrincipal: UserPrincipal,
        commentId: Long,
        request: DeleteCommentRequest
    ) {
        val comment = commentRepository.findByUserIdAndId(userPrincipal.id, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        val user = userRepository.findById(userPrincipal.id).orElse(null)
            ?: throw IllegalStateException("User not found with id: ${userPrincipal.id}")

        if (!passwordEncoder.matches(request.password, user.password))
            throw InvalidCredentialsException("Password", request.password)

        commentRepository.delete(comment)

    }
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        email = email,
        nickName = nickName,
        contents = contents,
    )
}