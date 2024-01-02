package com.teamsparta.jangtrello.domain.comment.service

import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun getCommentLists() : List<CommentResponse>

    fun getCommentList(cardId:Long, commentId:Long) : CommentResponse

    fun createComment(request : CreateCommentRequest) : CommentResponse

    fun updateComment(commentId:Long, request: UpdateCommentRequest ) : CommentResponse

    fun deleteComment(commentId : Long)

}