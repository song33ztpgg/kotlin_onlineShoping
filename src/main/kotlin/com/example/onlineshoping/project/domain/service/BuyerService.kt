package com.example.onlineshoping.project.domain.service


import com.example.onlineshoping.project.domain.dto.request.CreateBuyerRequest
import com.example.onlineshoping.project.domain.dto.response.BuyerResponse

interface BuyerService {

    fun singupBuyer(request : CreateBuyerRequest):BuyerResponse
}