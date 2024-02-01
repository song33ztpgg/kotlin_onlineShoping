package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse
import com.example.onlineshoping.project.domain.service.CartService

import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


import jakarta.servlet.http.HttpServletRequest   // jwt값 확인

@RestController
class CartController(
    private val cartService: CartService
) {

    //장바구니 조회
    @GetMapping("/carts")
    fun viewCart(): ResponseEntity<List<CartResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartService.viewCart())
    }

    //장바구니에 담기
    @PostMapping("/carts")
    fun addCart(@RequestBody addCartRequest: AddCartRequest): ResponseEntity<CartResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cartService.addCart(addCartRequest))

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //장바구니 결재
    //장바구니 결재 후 삭제
    @PostMapping("/carts/pay/{memberId}")
    fun paymentCart(@PathVariable memberId:Long): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartService.paymentCart(memberId))
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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