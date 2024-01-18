package com.teamsparta.jangtrello.domain.card.controller

import com.example.courseregistration.domain.exception.dto.ErrorResponse
import com.teamsparta.jangtrello.domain.card.dto.CardResponse
import com.teamsparta.jangtrello.domain.card.dto.CreateCardRequest
import com.teamsparta.jangtrello.domain.card.dto.UpdateCardRequest
import com.teamsparta.jangtrello.domain.cardlist.service.CardListService
import com.teamsparta.jangtrello.domain.exception.ModelNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cardList/{cardListId}/cards")
class CardController(
    private val cardListService: CardListService
) {
    @GetMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun getCards(
        @PathVariable cardListId : Long
    ):ResponseEntity<List<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardListService.getCards(cardListId))
    }

    @GetMapping("sorted/{sortBy}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun getCardsSorted(
        @PathVariable cardListId : Long,
        @PathVariable sortBy : String,
    ):ResponseEntity<List<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardListService.getCardsSorted(cardListId, sortBy))
    }

    @GetMapping("usernamed/{userName}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun getCardsUserNamed(
        @PathVariable cardListId : Long,
        @PathVariable userName : String,
    ):ResponseEntity<List<CardResponse>>{

        return ResponseEntity.status(HttpStatus.OK).body(cardListService.getCardsUserNamed(cardListId, userName))
    }

    @GetMapping("/{cardId}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun getCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long
    ): ResponseEntity<CardResponse>{

        return ResponseEntity.status(HttpStatus.OK).body(cardListService.getCard(cardId)) //cardListId,
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun createCard(
        @PathVariable cardListId : Long,
        @RequestBody request : CreateCardRequest
    ): ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardListService.createCard(cardListId, request))
    }
    @PutMapping("/{cardId}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun updateCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long,
        @RequestBody request : UpdateCardRequest
    ) : ResponseEntity<CardResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(cardListService.updateCard(cardId, request))
    }

    @DeleteMapping("/{cardId}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    fun deleteCard(
        @PathVariable cardListId : Long,
        @PathVariable cardId : Long,
    ) : ResponseEntity<Unit>{

        cardListService.deleteCard(cardListId, cardId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}