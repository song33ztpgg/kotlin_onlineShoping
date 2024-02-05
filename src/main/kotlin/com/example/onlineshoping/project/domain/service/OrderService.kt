package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateOrderRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateOrdersRequest
import com.example.onlineshoping.project.domain.dto.response.OrderResponse

interface OrderService {

    fun viewOrder(memberId:Long):List<OrderResponse>

    fun updateOrder(requet: UpdateOrdersRequest):OrderResponse
}