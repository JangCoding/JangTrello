package com.teamsparta.jangtrello.domain.comment.controller

import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.jangtrello.domain.comment.service.CommentService
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
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments())
    }

    @GetMapping("/{commentId}")
    fun getComment( @PathVariable cardListId : Long, @PathVariable cardId : Long, @PathVariable commentId: Long
    ): ResponseEntity<CommentResponse>
    {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComment(cardId, commentId))
    }

    @PostMapping()
    fun createComment(@PathVariable cardListId : Long, @PathVariable cardId : Long,
        @RequestBody request : CreateCommentRequest
    ): ResponseEntity<CommentResponse> {

       TODO()
    }

    @PutMapping("/{commentId}")
    fun updateCard(
        @PathVariable cardListId : Long, @PathVariable cardId : Long, @PathVariable commentId : Long,
        @RequestBody request : UpdateCommentRequest
    ) : ResponseEntity<CommentResponse> {

        TODO()
    }

    @DeleteMapping("/{commentId}")
    fun deleteCard(
        @PathVariable cardListId : Long, @PathVariable cardId : Long, @PathVariable commentId : Long
    ) : ResponseEntity<Unit> {

        TODO()
    }

}