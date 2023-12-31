package com.teamsparta.jangtrello.domain.comment.controller

import com.example.courseregistration.domain.exception.dto.ErrorResponse
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.jangtrello.domain.comment.service.CommentService
import com.teamsparta.jangtrello.domain.exception.InvalidCredentialsException
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cardList/{cardListId}/cards/{cardId}/comments")
class CommentController(
    private val commentService: CommentService // @Service 안하면 못찾음
) {
    @GetMapping()
    fun getComments( @PathVariable cardListId : Long, @PathVariable cardId : Long
    ): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments(cardId))
    }

    @GetMapping("/{commentId}")
    fun getComment( @PathVariable cardListId : Long, @PathVariable cardId : Long, @PathVariable commentId: Long
    ): ResponseEntity<CommentResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComment(commentId))
    }

    @PostMapping()
    fun createComment(@PathVariable cardListId : Long, @PathVariable cardId : Long,
        @RequestBody request : CreateCommentRequest
    ): ResponseEntity<CommentResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(cardId, request))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable cardListId : Long, @PathVariable cardId : Long, @PathVariable commentId : Long,
        @RequestBody request : UpdateCommentRequest
    ) : ResponseEntity<CommentResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentId, request))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable cardListId : Long, @PathVariable cardId : Long, @PathVariable commentId : Long,
        @RequestBody request:DeleteCommentRequest
    ) : ResponseEntity<Unit> {
        commentService.deleteComment(cardId,commentId, request)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleInvalidCredentialsException(e: InvalidCredentialsException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(e.message))
    }

}