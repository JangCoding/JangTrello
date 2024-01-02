package com.teamsparta.jangtrello.domain.exception

//별도처리 없이 예외를 던지기만 할 것이라 data class
data class ModelNotFoundException(val modelName:String, val id:Long) : RuntimeException(
    "Model $modelName not found with given id: $id"
)