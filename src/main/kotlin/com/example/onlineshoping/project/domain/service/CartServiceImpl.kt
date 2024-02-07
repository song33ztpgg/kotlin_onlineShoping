package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.exception.ErrorResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.*
import com.example.onlineshoping.project.domain.model.enum.DiscountStatus
import com.example.onlineshoping.project.domain.model.enum.OrdersStatus
import com.example.onlineshoping.project.domain.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val memberRepository: MemberRepository,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    private val addressRepository: AddressRepository
) : CartService {


    //장바구니 조회
    override fun viewCart(memberId: Long): List<CartResponse> {
        return cartRepository.findAllByMemberId(memberId).map { it.toResponse() }
//        return cartRepository.findAllById(memberId).map { it.toResponse() }
    }

    //장바구니에 담기
    override fun addCart(memberId: Long,request: AddCartRequest): CartResponse {
        val (productId,amount) = request

        val createCart = Cart(
            memberId = memberId,
            productId = productId,
            amount = amount
        )

        val savedCart = cartRepository.save(createCart)
        val mappingSaveCart = savedCart.toResponse()
        return mappingSaveCart
    }

    //장바구니 결제 후 삭제, 주문내역 생성
    @Transactional
    override fun paymentCart(memberId: Long):MemberResponse{

        //유저가 담은 장바구니가 있는지 확인
        val buyerCart = cartRepository.findAllByMemberId(memberId)
        val memberInfo = memberRepository.findByIdOrNull(memberId) ?:throw ModelNotFoundException("Member", memberId)
        var totalPay  = 0

        for(m in buyerCart){

            //장바구니에 담긴 productId를 통해 물건을 검색
            val productInfo = productRepository.findByIdOrNull(m.productId)?: throw ModelNotFoundException("Cart", memberId)

            //물건에 판매자 검색
            val sellerInfo = memberRepository.findByIdOrNull(productInfo.memberId)?: throw  ModelNotFoundException("Memeber", memberId)

            //수량이 문제 없는지 확인
            //수정할 에러 메세지
            if(productInfo.remainingStock < m.amount){
               throw ErrorResponse("재고가 부족합니다")
            }

            //수량 차감
            productInfo.remainingStock = productInfo.remainingStock - m.amount
            productRepository.save(productInfo)

            //할인된 최종금액
            var discontedPrice:Int

            //할인종류
            val discountTypeName = productInfo.discountType.name
            when(discountTypeName) {
                "fixedAmount" -> discontedPrice = productInfo.price - productInfo.discount
                "rate" -> discontedPrice = (productInfo.price * (100-productInfo.discount))/100
                else -> discontedPrice = productInfo.price
            }

            //판매자에게 금액 송금
            sellerInfo.account += discontedPrice * m.amount
            //전체금액 누적합산
            totalPay += discontedPrice * m.amount

            val addressInfo = addressRepository.findByMemberIdAndAddressDefault(memberId,true)

            //수정할 에러 메세지
            if(addressInfo == null) {
                throw ErrorResponse("기존 주소를 지정하지 않았습니다")
            }

            val createOrders = Orders(
                productId = productInfo.id!!,
                memberId = memberId,
                amount = m.amount,
                status =  OrdersStatus.결재완료,
                discountStatus = DiscountStatus.valueOf(discountTypeName),
                discount = productInfo.discount ,
                discountAmount = discontedPrice * m.amount,
                roadAddress = addressInfo.roadAddress
            )

            //구매내역을 생성
            orderRepository.save(createOrders)
        }

        //금액이 문제 없는지 확인
        //수정할 에러 메세지
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

        val mappingMemberInfo = memberInfo.toResponse()
        return mappingMemberInfo
    }

    override fun deleteCart(memberId: Long) {
        val memberCart = cartRepository.findByIdOrNull(memberId) ?: throw  ModelNotFoundException("cart",memberId)
        cartRepository.delete(memberCart)
    }



}


