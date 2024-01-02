package com.teamsparta.jangtrello.domain.card.controller

import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.cardlist.service.CardListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cardList/{cardListId}/cards")
class CardController(
    private val cardListService: CardListService
) {
    @GetMapping()
    fun getCards(
        @PathVariable cardListId : Long
    ):ResponseEntity<List<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardListService.getCards(cardListId))
    }

    @GetMapping("/{cardId}")
    fun getCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long
    ): ResponseEntity<CardResponse>{

        return ResponseEntity.status(HttpStatus.OK).body(cardListService.getCard(cardId)) //cardListId,
    }

    @PostMapping()
    fun createCard(
        @PathVariable cardListId : Long,
        @RequestBody request : CreateCardRequest
    ): ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardListService.createCard(cardListId, request))
    }
    @PutMapping("/{cardId}")
    fun updateCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long,
        @RequestBody request : UpdateCardRequest
    ) : ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardListService.updateCard(cardId, request))
    }

    @DeleteMapping("/{cardId}")
    fun deleteCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long,
    ) : ResponseEntity<Unit>{

        cardListService.deleteCard(cardListId, cardId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}