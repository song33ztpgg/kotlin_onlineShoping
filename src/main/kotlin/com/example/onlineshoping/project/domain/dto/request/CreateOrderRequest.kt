package com.example.onlineshoping.project.domain.dto.request

import java.util.Date

data class CreateOrderRequest (
    val buyerId:Long,
    val productId:Long,
    val amount:Int,
    val status:String
)