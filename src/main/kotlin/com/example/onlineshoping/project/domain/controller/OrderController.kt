package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateOrderRequest
import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import com.example.onlineshoping.project.domain.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("order")
class OrderController (
    private val orderService: OrderService
){

    //주문 내역 만들기(자동적으로 만들어져야 하는 부분)
    @PostMapping
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest):ResponseEntity<OrderResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(orderService.createOder(createOrderRequest))
    }

    //주문 내역 보기
    @GetMapping
    fun viewOrder():ResponseEntity<List<OrderResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(orderService.viewOrder())
    }
}