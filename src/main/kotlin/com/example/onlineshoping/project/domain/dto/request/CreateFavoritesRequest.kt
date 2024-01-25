package com.example.onlineshoping.project.domain.dto.request

data class CreateFavoritesRequest (
    val buyerId:Long,
    val productId:Long
)