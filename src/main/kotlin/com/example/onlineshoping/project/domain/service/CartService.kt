package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import org.springframework.security.core.userdetails.User


interface CartService {


    //장바구니 조회
    fun viewCart(memberId: Long):List<CartResponse>

    //장바구니에 담기
    fun addCart( memberId: Long,request : AddCartRequest):CartResponse

    //장바구니 결재
    fun paymentCart(memberId:Long):MemberResponse

    //장바구니 전체취소
    fun deleteCart(memberId: Long)




}