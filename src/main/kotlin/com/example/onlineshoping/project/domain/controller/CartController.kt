package com.example.onlineshoping.project.domain.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.*

@RestController
class CartController() {

    //상품담기
    @PostMapping("/carts")
    fun addCart(): RequestEntity<Unit> {
        TODO()
    }

    //장바구니 조회
    @GetMapping("/carts")
    fun viewCart(): RequestEntity<Unit> {
        TODO()
    }

    //장바구니 결재
    //장바구니 결재 후 삭제
    @PostMapping("/carts/pay")
    fun paymentCart(): RequestEntity<Unit> {
        TODO()
    }

    //장바구니 전체취소
    @DeleteMapping("/carts")
    fun deleteCart(): RequestEntity<Unit> {
        TODO()
    }


    //주문 취소
    @PutMapping("/orders/{orderId}/cancel")
    fun deleteCancelOrder(): RequestEntity<Unit> {
        TODO()
    }

}