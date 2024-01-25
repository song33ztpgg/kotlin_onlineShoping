package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateOrderRequest
import com.example.onlineshoping.project.domain.dto.response.OrderResponse

interface OrderService {

    fun viewOrder():List<OrderResponse>

    fun createOder(request: CreateOrderRequest):OrderResponse
}