package com.example.onlineshoping.project.domain.service

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping

interface CartService {


    //장바구니 조회
    fun viewCart()

    //장바구니에 추가
    fun addCart()

    //장바구니 결재
    fun paymentCart()

    //장바구니 결재 후 삭제
    fun deleteOrderCart()

    //주문 취소
    fun deleteCancelOrder()


}