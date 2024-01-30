package com.example.onlineshoping.project.domain.service


import com.example.onlineshoping.project.domain.dto.request.CreateBuyerRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.response.BuyerResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse

interface BuyerService {

    fun login(request: LoginRequest): LoginResponse

    fun singupBuyer(request: CreateBuyerRequest): BuyerResponse
}