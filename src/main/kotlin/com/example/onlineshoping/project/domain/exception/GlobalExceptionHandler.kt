package com.example.onlineshoping.project.domain.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import com.example.onlineshoping.project.domain.exception.ErrorResponse

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e:ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(e.message))
    }
}


/*@RestControllerAdvice
*ExceptionHandler
*(ModelNotFoundException::class)
*
* */