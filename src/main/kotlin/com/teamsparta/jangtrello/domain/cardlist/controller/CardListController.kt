package com.teamsparta.jangtrello.domain.cardlist.controller

import com.teamsparta.jangtrello.domain.cardlist.dto.CardListResponse
import com.teamsparta.jangtrello.domain.cardlist.dto.CreateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.dto.UpdateCardListRequest
import com.teamsparta.jangtrello.domain.cardlist.service.CardListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
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
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody request: UpdateCardListRequest
    ): ResponseEntity<CardListResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(cardlistService.updateCardList(cardListId, request))
    }

    @DeleteMapping("/{cardListId}")
    fun deleteCardList(
        @PathVariable cardListId: Long
    ) : ResponseEntity<Unit>  {
        cardlistService.deleteCardList(cardListId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}
