package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateOrderRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateOrdersRequest
import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Orders
import com.example.onlineshoping.project.domain.model.enum.OrdersStatus
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.OrderRepository
import com.example.onlineshoping.project.domain.repository.ProdcutRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import javax.xml.crypto.Data
import java.time.LocalDate
import java.util.*

@Service
class OrderserviceImpl(
    private val orderRepository: OrderRepository,
    private val prodcutRepository: ProdcutRepository
):OrderService {
    override fun viewOrder(memberId:Long): List<OrderResponse> {

        val findOrders = orderRepository.findAllByMemberId(memberId)
        val responseOrders = findOrders.map{it.toResponse()}
        return responseOrders
    }

    override fun updateOrder(member: User,request: UpdateOrdersRequest): OrderResponse {

        member.isEnabled
        val(orderId,status) = request
        val orderInfo = orderRepository.findByIdOrNull(orderId) ?: throw ModelNotFoundException("Orders",orderId)

        orderInfo.status  = OrdersStatus.valueOf(status)
        val updateOrder  = orderRepository.save(orderInfo)

        if( updateOrder.status.name == "결재취소"){
            val productInfo = prodcutRepository.findByIdOrNull(orderInfo.productId) ?:throw  ModelNotFoundException("Product",orderInfo.productId)
             productInfo.remainingStock += updateOrder.amount
            prodcutRepository.save(productInfo).toResponse()
        }


        return updateOrder.toResponse()
    }




}