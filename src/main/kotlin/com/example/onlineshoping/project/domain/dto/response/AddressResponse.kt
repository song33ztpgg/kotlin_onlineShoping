package com.example.onlineshoping.project.domain.dto.response

data class AddressResponse(
    val id:Long,
    val memberId:Long,
    val roadAddress : String,
    val addressDefault:Boolean
)
