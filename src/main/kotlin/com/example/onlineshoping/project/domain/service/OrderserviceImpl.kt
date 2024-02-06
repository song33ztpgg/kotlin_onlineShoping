package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateOrderRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateOrdersRequest
import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import com.example.onlineshoping.project.domain.exception.ErrorResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Orders
import com.example.onlineshoping.project.domain.model.enum.OrdersStatus
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.MemberRepository
import com.example.onlineshoping.project.domain.repository.OrderRepository
import com.example.onlineshoping.project.domain.repository.ProdcutRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import javax.xml.crypto.Data
import java.time.LocalDate
import java.util.*
import kotlin.jvm.Throws

@Service
class OrderserviceImpl(
    private val orderRepository: OrderRepository,
    private val prodcutRepository: ProdcutRepository,
    private val memberRepository: MemberRepository
):OrderService {
    override fun viewOrder(memberId:Long): List<OrderResponse> {

        val findOrders = orderRepository.findAllByMemberId(memberId)
        val responseOrders = findOrders.map{it.toResponse()}
        return responseOrders
    }

    override fun updateOrder(member: User,request: UpdateOrdersRequest): OrderResponse {

        when(member.authorities.toString()){
            "[ROLE_BUYER]" -> println("buyer")
            "[ROLE_SELLER]" -> println("seller")
            else -> println("xxxx")
        }
        val(orderId,status) = request
        val orderInfo = orderRepository.findByIdOrNull(orderId) ?: throw ModelNotFoundException("Orders",orderId)

        //기존 상태 확인 (중복채크)
        if(orderInfo.status  == OrdersStatus.valueOf(status)) {
            throw ErrorResponse("중복된 상황입니다")
        }

        //상태 전환 및 저장
        orderInfo.status  = OrdersStatus.valueOf(status)
        val updateOrder  = orderRepository.save(orderInfo)


        //저장된 상태에 따른 행동
        if( updateOrder.status.name == "결재취소"){
            //물품 재고 복구 시키기
            val productInfo = prodcutRepository.findByIdOrNull(orderInfo.productId) ?:throw  ModelNotFoundException("Product",orderInfo.productId)
             productInfo.remainingStock += updateOrder.amount
            prodcutRepository.save(productInfo).toResponse()

            //가격 돌려받기

            //물건id -> 판매자 찾음
            val refundProduct =prodcutRepository.findByIdOrNull(updateOrder.productId)!!
            var refundSeller = refundProduct.memberId //판매자
            var refundBuyer = updateOrder.memberId  // 구매자
            val refundAmount = updateOrder.discountAmount // 돌려받아야 함 금액 // 금액 = 10000 + 구매금액

            //구매자 판매자 물건가격만큼 복구
            val refundSellerInfo = memberRepository.findByIdOrNull(refundSeller) ?: throw ModelNotFoundException("Seller",refundSeller)
            val refundBuyerInfo = memberRepository.findByIdOrNull(refundBuyer) ?: throw ModelNotFoundException("Seller",refundBuyer)


            refundSellerInfo.account -= refundAmount
            refundBuyerInfo.account += refundAmount

            memberRepository.save(refundSellerInfo)
            memberRepository.save(refundBuyerInfo)


        } else if(updateOrder.status.name == "배송중" && member.authorities.toString() == "[ROLE_SELLER]"){

        } else {

        }

        return updateOrder.toResponse()
    }




}