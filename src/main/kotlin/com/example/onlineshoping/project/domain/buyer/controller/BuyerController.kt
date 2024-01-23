package com.example.onlineshoping.project.domain.buyer.controller

import com.example.onlineshoping.project.domain.buyer.dto.BuyerResponse
import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("buyer")
@RestController
class BuyerController() {

    @PostMapping("/singup/buyer")
    fun singupBuyer(@RequestBody buyerResponse: BuyerResponse):RequestEntity<Unit>{
        TODO()
    }

    @PutMapping("/updateBuyerInfo")
    fun updateUserInfo():RequestEntity<Unit>{
        TODO()
    }
}