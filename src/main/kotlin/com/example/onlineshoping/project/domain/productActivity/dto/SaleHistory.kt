package com.example.onlineshoping.project.domain.productActivity.dto

data class SaleHistory(
    val id:Long,
    val orderCartId:Long,
    val buyerId:Long,
    val status:String
)
