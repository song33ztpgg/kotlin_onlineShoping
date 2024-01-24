package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.ProductResponse
import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.*

@RestController
class CartController() {

    //상품담기
    @PostMapping("/carts")
    fun addCart(): RequestEntity<ProductResponse> {
        TODO()
    }

    //장바구니 조회
    @GetMapping("/carts")
    fun viewCart(): RequestEntity<Unit> {
        TODO()
    }

    //장바구니 결재
    @PostMapping("/carts/pay")
    fun paymentCart(): RequestEntity<Unit> {
        TODO()
    }

    //장바구니 결재 후 삭제
    @DeleteMapping("/carts")
    fun deleteOrderCart(): RequestEntity<Unit> {
        TODO()
    }

    //주문 취소
    @PutMapping("/orders/{orderId}/cancel")
    fun deleteCancelOrder(): RequestEntity<Unit> {
        TODO()
    }


    //즐겨찾기 추가
    @PostMapping("add-favorites")
    fun favorites(
//        @RequestBody favoriteRequest: FavoriteRequest
    ): RequestEntity<Unit> {
        TODO()
    }

    //즐겨찾기 목록
    @GetMapping("/favorites")
    fun viewAllmyFavoritesList(): RequestEntity<Unit> {
        TODO()
    }
}