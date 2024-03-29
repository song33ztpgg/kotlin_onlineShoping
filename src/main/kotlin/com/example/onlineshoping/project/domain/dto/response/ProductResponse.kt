package com.example.onlineshoping.project.domain.dto.response

data class ProductResponse(
    val id:Long,
    val memberId:Long,
    val category:String,
    val name:String,
    val price:Int,
    val discountType:String,
    val discount:Int,
    val productInfo:String,
    val remainingStock:Int,
    val favoritesCount:Int
)
