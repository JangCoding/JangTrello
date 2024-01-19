package com.teamsparta.jangtrello.domain.cardlist.controller

import com.teamsparta.jangtrello.domain.cardlist.service.CardListService

//@RestController
//@RequestMapping("/cardLists")
class CardListController(
    private val cardlistService : CardListService
) {
//
//    @GetMapping()
//    fun getCardLists(): ResponseEntity<List<CardListResponse>> {
//        return ResponseEntity.status(HttpStatus.OK).body(cardlistService.getCardLists())
//    }
//
//    @GetMapping("/{cardListId}")
//    fun getCardList(
//        @PathVariable cardListId: Long
//    ): ResponseEntity<CardListResponse> {
//        return ResponseEntity.status(HttpStatus.OK).body(cardlistService.getCardList(cardListId))
//    }
//
//    @PostMapping()
//    fun createCardList(
//        @RequestBody request: CreateCardListRequest
//    ): ResponseEntity<CardListResponse> {
//        return ResponseEntity.status(HttpStatus.CREATED).body(cardlistService.createCardList(request))
//    }
//
//    @PutMapping("/{cardListId}")
//
//    fun updateCardList(
//        @PathVariable cardListId: Long,
//        @AuthenticationPrincipal userDetails: UserDetails,
//        @RequestBody request: UpdateCardListRequest
//    ): ResponseEntity<CardListResponse> {
//        return ResponseEntity.status(HttpStatus.OK).body(cardlistService.updateCardList(cardListId, request))
//    }
//
//    @DeleteMapping("/{cardListId}")
//    fun deleteCardList(
//        @PathVariable cardListId: Long
//    ) : ResponseEntity<Unit>  {
//        cardlistService.deleteCardList(cardListId)
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
//    }

}
