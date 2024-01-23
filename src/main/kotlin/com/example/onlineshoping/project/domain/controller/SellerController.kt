package com.example.onlineshoping.project.domain.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("seller")
class SellerController() {

    //판매자 회원가입
    @PostMapping
    fun singupSeller():RequestEntity<Unit>{
         TODO()
    }
}