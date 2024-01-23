package com.example.onlineshoping.project.domain.seller.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/seller")
@RestController
class SellerController() {

    @PostMapping("/singup/seller")
    fun singupSeller():RequestEntity<Unit>{
        TODO()
    }

}