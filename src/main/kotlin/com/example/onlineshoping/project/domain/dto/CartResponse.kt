package com.example.onlineshoping.project.domain.dto

data class CartResponse(
    val id:Long,
    val productId:Long,
    val buyerId:Long,
    val amoutn:Int,
    val status:String
)
