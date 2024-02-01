package com.example.onlineshoping.project.domain.dto.response

data class CartResponse(
    val id:Long,
    val productId:Long,
    val memberId:Long,
    val amount:Int
)
