package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateBuyerRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.response.BuyerResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import com.example.onlineshoping.project.domain.service.BuyerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class BuyerController(
    private val buyerService: BuyerService
) {

//    @PostMapping
//    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
//        return ResponseEntity
//            .status(HttpStatus.OK)
//            .body(buyerService.login(loginRequest))
//    }


    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest) :ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(buyerService.login(loginRequest))
    }


    @PostMapping("/buyer/signup")
    fun singupBuyer(@RequestBody createBuyerRequest: CreateBuyerRequest): ResponseEntity<BuyerResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(buyerService.singupBuyer(createBuyerRequest))
    }


}