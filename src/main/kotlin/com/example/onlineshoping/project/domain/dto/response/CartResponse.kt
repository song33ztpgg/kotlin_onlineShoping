package com.example.onlineshoping.project.domain.dto.response

data class CartResponse(
    val id:Long,
    val buyerId:Long,
    val productId:Long,
    val amount:Int,
    val status:String
)
