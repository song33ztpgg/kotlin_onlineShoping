package com.example.onlineshoping.project.domain.dto.request

data class CreateBuyerRequest(
    val email:String,
    val password:String,
    val name:String,
    val phoneNumber:String,
    val balance:Long
)
