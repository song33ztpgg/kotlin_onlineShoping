package com.example.onlineshoping.project.domain.dto.response

import java.util.*

data class OrderResponse(
    val id:Long,
    val buyerId:Long,
    val productId:Long,
    val amount:Int,
    val status:String,
    val orderDate: Date
)
