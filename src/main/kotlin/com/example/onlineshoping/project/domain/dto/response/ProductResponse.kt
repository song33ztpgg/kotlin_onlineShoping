package com.example.onlineshoping.project.domain.dto.response

data class ProductResponse(
    val id:Long,
    val sellerId:Long,
    val category:String,
    val name:String,
    val price:Long,
    val discountType:String,
    val discount:Int,
    val productInfo:String,
    val remainingStock:Int
)
