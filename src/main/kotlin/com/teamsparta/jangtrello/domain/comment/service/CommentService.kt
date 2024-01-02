package com.teamsparta.jangtrello.domain.comment.service

import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun getComments() : List<CommentResponse>

    fun getComment(commentId:Long) : CommentResponse

    fun createComment(cardId: Long, request : CreateCommentRequest) : CommentResponse

    fun updateComment(commentId:Long, request: UpdateCommentRequest ) : CommentResponse

    fun deleteComment(cardId: Long, commentId : Long)

}