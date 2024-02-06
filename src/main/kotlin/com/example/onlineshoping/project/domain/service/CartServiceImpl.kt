package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import com.example.onlineshoping.project.domain.exception.ErrorResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.*
import com.example.onlineshoping.project.domain.model.enum.OrdersStatus
import com.example.onlineshoping.project.domain.repository.CartRepository
import com.example.onlineshoping.project.domain.repository.MemberRepository
import com.example.onlineshoping.project.domain.repository.OrderRepository
import com.example.onlineshoping.project.domain.repository.ProdcutRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val memberRepository: MemberRepository,
    private val prodcutRepository: ProdcutRepository,
    private val orderRepository: OrderRepository
) : CartService {


    //장바구니 조회
    override fun viewCart(memberId: Long): List<CartResponse> {
        return cartRepository.findAllByMemberId(memberId).map { it.toResponse() }
//        return cartRepository.findAllById(memberId).map { it.toResponse() }
    }

    //장바구니에 담기
    override fun addCart(memberId: Long,request: AddCartRequest): CartResponse {
        val cart = Cart(
            memberId = memberId,
            productId = request.productId,
            amount = request.amount
        )

        val savedCart = cartRepository.save(cart)
        return savedCart.toResponse()
    }

    //장바구니 결재
    //정버규나 결제 후 삭제
    @Transactional
    override fun paymentCart(memberId: Long):MemberResponse{

        //유저가 담은 장바구니가 있는지 확인
        val buyerCart = cartRepository.findAllByMemberId(memberId)
        val memberInfo = memberRepository.findByIdOrNull(memberId) ?:throw ModelNotFoundException("Member", memberId)
        var totalPay  = 0


        for(m in buyerCart){
            //장바구니에 담긴 productId를 통해 물건을 검색
            val productInfo = prodcutRepository.findByIdOrNull(m.productId)?: throw ModelNotFoundException("Cart", memberId)
            //물건에 판매자 검색
            val sellerInfo = memberRepository.findByIdOrNull(productInfo.memberId)?: throw  ModelNotFoundException("Memeber", memberId)

            //수량이 문제 없는지 확인
            if(productInfo.remainingStock < m.amount){
               throw ErrorResponse("재고가 부족합니다")
            }

            //수량 차감
            productInfo.remainingStock = productInfo.remainingStock - m.amount
            prodcutRepository.save(productInfo)


            var discontedPrice:Int
            var discountTypeName = productInfo.discountType.name
            when(discountTypeName) {
                "fixedAmount" -> discontedPrice = productInfo.price - productInfo.discount
                "rate" -> discontedPrice = (productInfo.price * (100-productInfo.discount))/100
                else -> discontedPrice = productInfo.price
            }


            //판매자에게 금액 송금
            sellerInfo.account += discontedPrice * m.amount

            //전체금액 누적합산
                totalPay += discontedPrice * m.amount

            val orders = Orders(
                productId = productInfo.id!!,
                memberId = memberId,
                amount = m.amount,
                status =  OrdersStatus.결재완료,
                roadAddress = "임시주소"
            )


            //구매내역을 생성
            orderRepository.save(orders)
//            val saveOrders = orderRepository.save(orders)
//            saveOrders.toResponse()
        }

        //금액이 문제 없는지 확인
        if(memberInfo.account < totalPay){
            throw ErrorResponse("금액이 부족합니다")
        }

        //구매자의 계좌에 구입 물품금액만큼 차감
        memberInfo.account = memberInfo.account - totalPay
        memberRepository.save(memberInfo)

        //장바구니 삭제
        for(m in buyerCart){
            cartRepository.delete(m)
        }

        return memberInfo.toResponse()
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun deleteCancelOrder() {
        TODO("Not yet implemented")
    }


    override fun deleteCart() {
        TODO("Not yet implemented")
    }
}


