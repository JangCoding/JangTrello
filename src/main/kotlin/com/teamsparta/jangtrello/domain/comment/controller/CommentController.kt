package com.teamsparta.jangtrello.domain.comment.controller

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.comment.dto.CommentResponse
import com.teamsparta.jangtrello.domain.comment.dto.CreateCommentRequest
import com.teamsparta.jangtrello.domain.comment.dto.UpdateCommentRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.xml.stream.events.Comment

@RestController
@RequestMapping("/cardList/{cardListId}/cards/{cardId}/comments")
class CommentController {
    @GetMapping()
    fun getComments( @PathVariable cardListId : Long, @PathVariable cardId : Long
    ): ResponseEntity<List<CommentResponse>> {

        TODO()
    }

    @GetMapping("/{commentId}")
    fun getComment( @PathVariable cardListId : Long, @PathVariable cardId : Long
    ): ResponseEntity<CommentResponse>
    {
        TODO()
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

    @PutMapping("/{commentId}")
    fun deleteCard(
        @PathVariable cardListId : Long, @PathVariable cardId : Long, @PathVariable commentId : Long
    ) : ResponseEntity<Unit> {

        TODO()
    }

}