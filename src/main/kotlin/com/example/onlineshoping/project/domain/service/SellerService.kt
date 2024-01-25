package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateSellerRequest
import com.example.onlineshoping.project.domain.dto.response.SellerResponse

interface SellerService{
    fun singupSeller(request: CreateSellerRequest): SellerResponse
}