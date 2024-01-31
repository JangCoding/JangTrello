package com.teamsparta.jangtrello.domain.cardlist.service


//@Service
//class CardListServiceImpl(
//    private val cardListRepository: CardListRepository,
//    private val userRepository: UserRepository,
//) : CardListService{
//    override fun getCardLists(): List<CardListResponse> {
//        return cardListRepository.findAll().map { it.toResponse() }
//    }
//
//    override fun getCardList(cardListId: Long): CardListResponse {
//        val cardList = cardListRepository.findByIdOrNull(cardListId) ?: throw ModelNotFoundException("CardList", cardListId)
//        return cardList.toResponse()
//
//    }
//
//    @Transactional
//    override fun createCardList(request: CreateCardListRequest): CardListResponse {
//
//        return cardListRepository.save(
//            CardList(
//                title = request.title,
//                //date 는 자동 생성
//                userName = request.details.username,
//            )
//        ).toResponse()
//    }
//
//    @Transactional
//    override fun updateCardList(cardListId: Long, request: UpdateCardListRequest): CardListResponse {
//        val cardList = cardListRepository.findByIdOrNull(cardListId) ?: throw ModelNotFoundException("CardList", cardListId)
//        val (title, description) = request
//
//        cardList.title = title
//        cardList.userName = request.details.username
//
//        return cardListRepository.save(cardList).toResponse()
//    }
//    @Transactional
//    override fun deleteCardList(cardListId: Long) {
//        val cardList = cardListRepository.findByIdOrNull(cardListId) ?: throw ModelNotFoundException("CardList", cardListId)
//        cardListRepository.delete(cardList)
//    }
//
//
//
//}
//
//fun chkInputValidation(title:String, contents : String) : Boolean{
//    if(title.length in 1..200 && contents.length in 1..1000)
//        return true
//    else
//        return false
//}