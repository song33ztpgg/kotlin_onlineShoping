package com.example.onlineshoping.project.domain.exception

data class ErrorResponse(val errorMessage: String?) : RuntimeException(errorMessage)





