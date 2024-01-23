package com.example.onlineshoping.project.domain.dto

data class SaleHistory(
    val id:Long,
    val cartId:Long,
    val buyerId:Long,
    val status:String
)
