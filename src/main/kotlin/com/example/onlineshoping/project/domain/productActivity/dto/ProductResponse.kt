package com.example.onlineshoping.project.domain.productActivity.dto

data class ProductResponse(
    val id:Long,
    val sellerId:Long,
    val category:String,
    val name:String,
    val price:Int,
    val productInfo:String,
    val remainingStock:Int
)
