package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.service.CartService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.annotation.AuthenticationPrincipal

@RestController
@RequestMapping("/carts")
class CartController(
    private val cartService: CartService
) {


    //장바구니에 담기
    @PutMapping
    fun addCart(
        @AuthenticationPrincipal member: User,
        @RequestBody addCartRequest: AddCartRequest
    ): ResponseEntity<CartResponse> {
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cartService.addCart(memberId, addCartRequest))
    }

    //장바구니 조회
    @GetMapping
    fun viewCart(@AuthenticationPrincipal member: User): ResponseEntity<List<CartResponse>> {
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartService.viewCart(memberId))
    }

    //장바구니 결재 후 삭제
    @PostMapping("/pay")
    fun paymentCart(@AuthenticationPrincipal member: User): ResponseEntity<MemberResponse> {
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cartService.paymentCart(memberId))
    }

    //    장바구니 전체취소
    @DeleteMapping("/carts")
    fun deleteCart(@AuthenticationPrincipal member: User): ResponseEntity<Unit> {
        val memberId = member.username.toLong()

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(cartService.deleteCart(memberId))
    }

}