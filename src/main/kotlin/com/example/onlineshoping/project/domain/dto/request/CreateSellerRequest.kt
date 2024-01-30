package com.example.onlineshoping.project.domain.dto.request

data class CreateSellerRequest (
    val email:String,
    val name:String,
    val password:String,
    val account:Long
)