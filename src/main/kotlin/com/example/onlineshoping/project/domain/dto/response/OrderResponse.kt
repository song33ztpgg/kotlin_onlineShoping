package com.example.onlineshoping.project.domain.dto.response

import java.util.*

data class OrderResponse(
    val id:Long,
    val productId:Long,
    val memberId :Long,
    val amount:Int,
    val status:String,
    val orderDate: Date,
    val roadAddress:String
)
