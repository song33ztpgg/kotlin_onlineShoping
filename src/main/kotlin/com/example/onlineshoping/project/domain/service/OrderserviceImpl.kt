package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.UpdateOrdersRequest
import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import com.example.onlineshoping.project.domain.exception.ErrorResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.enum.OrdersStatus
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.MemberRepository
import com.example.onlineshoping.project.domain.repository.OrderRepository
import com.example.onlineshoping.project.domain.repository.ProductRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class OrderserviceImpl(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val memberRepository: MemberRepository
):OrderService {
    override fun viewOrder(memberId:Long): List<OrderResponse> {
        val findOrders = orderRepository.findAllByMemberId(memberId)
        val mappingFindOrders = findOrders.map{it.toResponse()}
        return mappingFindOrders
    }

    @Transactional
    override fun updateOrder(member: User,request: UpdateOrdersRequest): OrderResponse {
        val(orderId,status) = request

        when(member.authorities.toString()){
            "[ROLE_BUYER]" -> println("buyer")
            "[ROLE_SELLER]" -> println("seller")
        }

        val orderInfo = orderRepository.findByIdOrNull(orderId) ?: throw ModelNotFoundException("Orders",orderId)

        //기존 상태 확인 (중복채크)
        //수정할 에러 메세지
        if(orderInfo.status  == OrdersStatus.valueOf(status)) {
            throw ErrorResponse("중복된 상황입니다")
        }

        if(status == "배송중" && member.authorities.toString() == "[ROLE_SELLER]"){
            throw ErrorResponse("판매자는 '배송중'으로 수정할 수 없습니다")
        }

        //상태 전환 및 저장
        orderInfo.status  = OrdersStatus.valueOf(status)
        val updateOrder  = orderRepository.save(orderInfo)


        //저장된 상태에 따른 행동
        if( updateOrder.status.name == "결재취소"){
            //물품 재고 복구 시키기
            val productInfo = productRepository.findByIdOrNull(orderInfo.productId) ?:throw  ModelNotFoundException("Product",orderInfo.productId)
             productInfo.remainingStock += updateOrder.amount
            productRepository.save(productInfo).toResponse()

            //가격 돌려받기
            //물건id -> 판매자 찾음
            val refundProduct =productRepository.findByIdOrNull(updateOrder.productId)!!
            var refundSeller = refundProduct.memberId //판매자
            var refundBuyer = updateOrder.memberId  // 구매자
            val refundAmount = updateOrder.discountAmount // 돌려받아야 함 금액

            //구매자 판매자 물건가격만큼 복구
            val refundSellerInfo = memberRepository.findByIdOrNull(refundSeller) ?: throw ModelNotFoundException("Seller",refundSeller)
            val refundBuyerInfo = memberRepository.findByIdOrNull(refundBuyer) ?: throw ModelNotFoundException("Seller",refundBuyer)

            refundSellerInfo.account -= refundAmount
            refundBuyerInfo.account += refundAmount

            memberRepository.save(refundSellerInfo)
            memberRepository.save(refundBuyerInfo)

        }

        val mappingUpdateOrders = updateOrder.toResponse()
        return mappingUpdateOrders
    }
}