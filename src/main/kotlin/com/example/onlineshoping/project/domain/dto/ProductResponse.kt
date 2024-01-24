package com.example.onlineshoping.project.domain.dto

data class ProductResponse(
    val id:Long,
    val sellerId:Long,
    val category:String,
    val name:String,
    val price:Int,
    val discountType:String,
    val difcount:Short,
    val productInfo:String,
    val remainingStock:Int
)
