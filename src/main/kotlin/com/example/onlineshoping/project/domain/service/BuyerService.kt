package com.example.onlineshoping.project.domain.service


import com.example.onlineshoping.project.domain.dto.request.CreateBuyer
import com.example.onlineshoping.project.domain.dto.response.BuyerResponse
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity

interface BuyerService {

    fun singupBuyer(request : CreateBuyer):BuyerResponse
}