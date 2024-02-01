package com.example.onlineshoping.project.domain.dto.request

data class AddCartRequest (
    val productId : Long,
    val memberId:Long,
    val amount :Int
)