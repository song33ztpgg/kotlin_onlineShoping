package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateSellerRequest
import com.example.onlineshoping.project.domain.dto.response.SellerResponse
import com.example.onlineshoping.project.domain.service.SellerService
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/seller")
class SellerController(
    private val sellerService: SellerService
) {

    //판매자 회원가입
    @PostMapping("/signup")
    fun singupSeller(@RequestBody createSellerRequest: CreateSellerRequest): ResponseEntity<SellerResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(sellerService.signupSeller(createSellerRequest))
    }
}