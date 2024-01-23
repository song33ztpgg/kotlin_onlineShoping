package com.example.onlineshoping.project.domain.buyerActivity.dto

data class OrderCartResponse(
    val id:Long,
    val buyerId:Long,
    val productId:Long,
    val amount:Int
)
