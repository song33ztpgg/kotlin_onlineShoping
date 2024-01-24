package com.example.onlineshoping.project.domain.dto

import java.util.Date

data class OrderResponse(
    val id:Long,
    val productId:Long,
    val buyerId:Long,
    val amount:Int,
    val status:String,
    val orderDate:Date
)
