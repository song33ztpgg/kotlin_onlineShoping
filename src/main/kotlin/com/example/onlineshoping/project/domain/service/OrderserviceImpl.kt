package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateOrderRequest
import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import com.example.onlineshoping.project.domain.model.Orders
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.OrderRepository
import org.springframework.stereotype.Service
import javax.xml.crypto.Data
import java.time.LocalDate

@Service
class OrderserviceImpl(
    private val orderRepository: OrderRepository
):OrderService {
    override fun viewOrder(): List<OrderResponse> {
        return orderRepository.findAll().map { it.toResponse() }
    }

    override fun createOder(request: CreateOrderRequest): OrderResponse {
        return orderRepository.save(
            Orders(
                buyer_id = request.buyerId,
                product_id =  request.productId,
                amount = request.amount,
                status = request.status,
            )
        ).toResponse()
    }
}