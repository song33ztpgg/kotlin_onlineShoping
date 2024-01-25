package com.example.onlineshoping.project.domain.dto.request

data class CreateBuyerRequest(
    val password:String,
    val name:String,
    val phoneNumber:String,
    val addressType: String,
    val buyerBalance:Long
)
