package com.example.onlineshoping.project.domain.dto.request

data class AddCartRequest (
    val buyerId:Long,
    val productId : Long,
    val amount :Int
)