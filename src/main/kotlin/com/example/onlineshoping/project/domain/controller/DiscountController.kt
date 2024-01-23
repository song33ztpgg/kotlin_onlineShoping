package com.example.onlineshoping.project.domain.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("discounts")
class DiscountController() {


    @PostMapping
    fun discount():RequestEntity<Unit>{
        TODO()
    }
}