package com.example.onlineshoping.project.domain.dto.response

data class MemberResponse(
    val id:Long,
    val email:String,
    val password:String,
    val name:String,
    val account:Long,
    val phoneNumber:String,
    val role:String
)
