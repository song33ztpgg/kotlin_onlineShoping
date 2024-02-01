package com.example.onlineshoping.project.domain.dto.response

data class AddressResponse(
    val id:Long,
    val memberId:Long,
    val road_address : String,
    val is_default:Boolean
)
