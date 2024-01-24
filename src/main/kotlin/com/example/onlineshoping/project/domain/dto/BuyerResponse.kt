package com.example.onlineshoping.project.domain.dto

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address

data class BuyerResponse(
    val id:Long,
    val password:String,
    val name:String,
    val phoneNumber:String,
    val addressType: String,
    val buyerBalance:Int
)