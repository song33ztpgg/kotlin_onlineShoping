package com.example.onlineshoping.project.domain.dto.request

data class CreateSellerRequest (
    val name:String,
    val password:String,
    val account:Long
)