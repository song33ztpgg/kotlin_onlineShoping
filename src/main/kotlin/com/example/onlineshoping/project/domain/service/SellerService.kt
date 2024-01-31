package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateSellerRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import com.example.onlineshoping.project.domain.dto.response.SellerResponse

interface SellerService{
    fun signupSeller(request: CreateSellerRequest): SellerResponse
}