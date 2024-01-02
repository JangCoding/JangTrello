package com.teamsparta.jangtrello.domain.cardlist.controller

import com.example.courseregistration.domain.exception.dto.ErrorResponse
import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.cardlist.dto.CardListResponse
import com.teamsparta.jangtrello.domain.cardlist.dto.CreateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.dto.UpdateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.service.CardListService
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cardLists")
class CardListController(
    private val cardlistService : CardListService
) {

    @GetMapping()
    fun getCardLists(): ResponseEntity<List<CardListResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(cardlistService.getCardLists())
    }

    @GetMapping("/{cardListId}")
    fun getCardList(
        @PathVariable cardListId: Long
    ): ResponseEntity<CardListResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(cardlistService.getCardList(cardListId))
    }

    @PostMapping()
    fun createCardList(
        @RequestBody request: CreateCardListRequest
    ): ResponseEntity<CardListResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardlistService.createCardList(request))
    }

    @PutMapping("/{cardListId}")
    fun updateCardList(
        @PathVariable cardListId: Long,
        @RequestBody request: UpdateCardListRequest
    ): ResponseEntity<CardListResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(cardlistService.updateCardList(cardListId, request))
    }

    @DeleteMapping("/{cardListId}")
    fun deleteCardList(
        @PathVariable cardListId: Long
    ) : ResponseEntity<Unit>  {
        cardlistService.deleteCardList(cardListId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.message))
    }
}
