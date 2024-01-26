package com.example.onlineshoping.project.domain.dto.response

data class AddressResponse(
    val id:Long,
    val buyerId:Long,
    val road_address : String,
    val is_default:Boolean
)
