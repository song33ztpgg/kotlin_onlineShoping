package com.example.onlineshoping.project.domain.dto.request

import java.util.Date

data class CreateOrderRequest (
    val productId:Long,
    val buyerId:Long,
    val amount:Int,
    val status:String,
    val orderDate: Date
)