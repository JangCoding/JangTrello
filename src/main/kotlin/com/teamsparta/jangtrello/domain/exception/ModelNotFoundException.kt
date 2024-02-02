package com.teamsparta.jangtrello.domain.exception

import java.io.Serial

//별도처리 없이 예외를 던지기만 할 것이라 data class
class ModelNotFoundException : RuntimeException {

    @Serial
    private val serialVersionUID: Long = -4937536120992271478L

    //상위 클래스의 생성자를 호출
    constructor() : super("Model Not Found")
    constructor(modelName: String) : super("Model [$modelName] Not Found ")
    constructor(modelName: String, findBy : String) : super("Model [$modelName] Not Found by [$findBy]")
    constructor(modelName: String, id: Long) : super("Model [$modelName] not found with given id: [$id]")

}