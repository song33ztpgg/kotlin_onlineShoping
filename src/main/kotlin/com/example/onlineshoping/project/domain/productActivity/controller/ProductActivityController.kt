package com.example.onlineshoping.project.domain.productActivity.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("productActivity")
@RestController
class ProductActivityController() {

    @PostMapping("/createProduct")
    fun createProduct():RequestEntity<Unit>{
        TODO()
    }

    @PutMapping("/updateProduct")
    fun updateProduct():RequestEntity<Unit>{
        TODO()
    }

    @PostMapping("/updateDiscount")
    fun updateDiscount():RequestEntity<Unit>{
        TODO()
    }

    
}