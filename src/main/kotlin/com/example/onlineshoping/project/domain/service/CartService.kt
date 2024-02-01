package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse


interface CartService {


    //장바구니 조회
    fun viewCart():List<CartResponse>

    //장바구니에 담기
    fun addCart( request : AddCartRequest):CartResponse

    //장바구니 결재
    fun paymentCart(memberId:Long)

    //장바구니 전체취소
    fun deleteCart()

    //주문 취소
    fun deleteCancelOrder()


}