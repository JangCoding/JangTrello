package com.teamsparta.jangtrello.domain.comment.controller

import com.example.courseregistration.domain.exception.dto.ErrorResponse
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.jangtrello.domain.comment.service.CommentService
import com.teamsparta.jangtrello.domain.exception.InvalidCredentialsException
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import com.teamsparta.jangtrello.infra.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cards/{cardId}/comments")
class CommentController(
    private val commentService: CommentService // @Service 안하면 못찾음
) {
    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun getComments(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable cardId : Long
    ): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(commentService.getComments(userPrincipal, cardId))
    }

    @GetMapping("/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun getComment(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable commentId: Long
    ): ResponseEntity<CommentResponse>
    {
        return ResponseEntity.status(HttpStatus.OK)
            .body(commentService.getComment(userPrincipal, commentId))
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun createComment(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable cardId : Long,
        @RequestBody request : CreateCommentRequest
    ): ResponseEntity<CommentResponse> {

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(commentService.createComment(userPrincipal, cardId, request))
    }

    @PutMapping("/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun updateComment(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable commentId : Long,
        @RequestBody request : UpdateCommentRequest
    ) : ResponseEntity<CommentResponse> {

        return ResponseEntity.status(HttpStatus.OK)
            .body(commentService.updateComment(userPrincipal, commentId, request))
    }

    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun deleteComment(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable commentId : Long,
        @RequestBody request:DeleteCommentRequest
    ) : ResponseEntity<Unit> {
        commentService.deleteComment(userPrincipal,commentId, request)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleInvalidCredentialsException(e: InvalidCredentialsException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(e.message))
    }

}