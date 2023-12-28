package com.example.courseregistration.domain.exception

import com.example.courseregistration.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {    // 전역적으로 예외 처리.
    @ExceptionHandler(ModelNotFoundException::class)
    // e :  예외 코드를 인자로, 반환은 Unit. 없음.
    // 메세지를 주고 싶으면 dto 를 만들어야함.
    fun handleModelNotFoundException(e:ModelNotFoundException) : ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(message = e.message)) // 예외 메세지 그대로 전달
    }
    // 다른 예외가 발생할 때 마다 아래 추가
                     // 기본 에러 메세지 중 1
    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(
        e:IllegalStateException
    ):ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse(e.message))
    }
}