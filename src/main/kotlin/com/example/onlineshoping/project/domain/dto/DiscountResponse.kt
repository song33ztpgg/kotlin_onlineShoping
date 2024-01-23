package com.example.onlineshoping.project.domain.dto

data class DiscountResponse(
    val id:Long,
    val productId:Long,
    val discountRate:Short?
)
