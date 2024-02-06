package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateOrderRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateOrdersRequest
import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import com.example.onlineshoping.project.domain.model.Member
import com.example.onlineshoping.project.domain.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrderController(
    private val orderService: OrderService
) {

    //주문 내역 보기
    @GetMapping("/myList")
    fun viewOrder(@AuthenticationPrincipal member: User): ResponseEntity<List<OrderResponse>> {
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.viewOrder(memberId))
    }

    //장바구니 수정
    @PutMapping("/cancel")
    fun updateOrder(
        @AuthenticationPrincipal member: User,
        @RequestBody updateOrdersRequest: UpdateOrdersRequest): ResponseEntity<OrderResponse> {
//        val memberId = member.username.toLong()

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.updateOrder(member,updateOrdersRequest))
    }

}