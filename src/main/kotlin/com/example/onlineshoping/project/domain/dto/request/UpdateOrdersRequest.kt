package com.example.onlineshoping.project.domain.dto.request

data class UpdateOrdersRequest(
    val orderId:Long,
    val memberId:Long,
    val status:String
)
