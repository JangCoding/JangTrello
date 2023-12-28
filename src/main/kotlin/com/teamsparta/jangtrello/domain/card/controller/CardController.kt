package com.teamsparta.jangtrello.domain.card.controller

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.card.service.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cardList/{cardListId}/cards")
class CardController(
    private val cardService : CardService
) {
    @GetMapping()
    fun getCards(
        @PathVariable cardListId : Long
    ):ResponseEntity<List<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCards())
    }

    @GetMapping("/{cardId}")
    fun getCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long
    ): ResponseEntity<CardResponse>{

        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCardById(cardId))
    }

    @PostMapping()
    fun createCard(
        @PathVariable cardListId : Long,
        @RequestBody request : CreateCardRequest
    ): ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.createCard(request))
    }
    @PutMapping("/{cardId}")
    fun updateCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long,
        @RequestBody request : UpdateCardRequest
    ) : ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.updateCard(cardListId, request))
    }

    @DeleteMapping("/{cardId}")
    fun deleteCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long,
        @RequestBody request : CreateCardRequest
    ) : ResponseEntity<Unit>{

        cardService.deleteCard(cardListId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}