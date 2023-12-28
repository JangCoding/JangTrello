package com.example.courseregistration.domain.exception.dto

data class ErrorResponse(
    val message:String?, // null 일 수도
    //val errorCode:String // enum 으로 하면 JSON 보낼때 변환해야됨
)

enum class ErrorCode{
    EMAIL_NOT_EXIST,
    INVALID_PASSWORD
}
