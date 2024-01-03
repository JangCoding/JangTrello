package com.teamsparta.jangtrello.domain.exception

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
    fun handleIllegalStateException(e:IllegalStateException):ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentialsException(e:InvalidCredentialsException) : ResponseEntity<ErrorResponse>{
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(message = e.message)) // 예외 메세지 그대로 전달
    }

    @ExceptionHandler(InvalidInputException::class)
    fun handleInvalidInputException(e:InvalidInputException) : ResponseEntity<ErrorResponse>{
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY) //서버가 요청을 이해했지만, 처리할 수 없거나, 서버에서 요청을 처리하는 동안 유효성 검사 오류가 발생했음
            .body(ErrorResponse(message = e.message)) // 예외 메세지 그대로 전달
    }
}