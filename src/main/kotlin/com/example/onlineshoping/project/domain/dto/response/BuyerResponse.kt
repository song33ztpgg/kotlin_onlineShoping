package com.example.onlineshoping.project.domain.dto.response

data class BuyerResponse(
    val id:Long,
    val email:String,
    val password:String,
    val name:String,
    val phoneNumber:String,
    val balance:Int
)
