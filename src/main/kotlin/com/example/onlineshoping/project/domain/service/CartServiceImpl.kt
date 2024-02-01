package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse
import com.example.onlineshoping.project.domain.exception.ErrorResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.*
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
    override fun viewCart(): List<CartResponse> {
        return cartRepository.findAll().map { it.toResponse() }
    }

    //장바구니에 담기
    override fun addCart(request: AddCartRequest): CartResponse {
        val cart = Cart(
            product_id = request.productId,
            member_id = request.memberId,
            amount = request.amount
        )

        val savedCart = cartRepository.save(cart)
        return savedCart.toResponse()
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //장바구니 결재
    //정버규나 결제 후 삭제
    @Transactional
    override fun paymentCart(memberId: Long) {
        //유저가 담은 장바구니가 있는지 확인
        val memberCart = cartRepository.findAllBymemberId(memberId)?: throw ModelNotFoundException("Cart", memberId)
        val memberInfo = memberRepository.findByIdOrNull(memberId) ?:throw ModelNotFoundException("Member", memberId)
        var totalPay  = 0

        for(m in memberCart){
            //장바구니에 담긴 productId를 통해 물건을 검색
            val productInfo = prodcutRepository.findByIdOrNull(m.product_id)?: throw ModelNotFoundException("Cart", memberId)

            //수량이 문제 없는지 확인
            if(productInfo.remaining_stock < m.amount){
                ErrorResponse("재고가 부족합니다")
            }

            //전체금액 누적합산
            totalPay += productInfo.price * m.amount

            val orders = Orders(
                product_id = productInfo.id!!,
                member_id = memberId,
                amount = m.amount,
                status =   "주문완료",//OrderStatus.주문완료,
                road_address = "임시주소"
            )

            val saveOrders = orderRepository.save(orders)
            saveOrders.toResponse()
        }

        //금액이 문제 없는지 확인
        if(memberInfo.account > totalPay){
            ErrorResponse("금액이 부족합니다")
        }





        //오더에 저장을 하기
        //장바구니 삭제
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


