package com.example.onlineshoping.project.domain.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController() {

    //상품제작, 판매자만 가능
    @PostMapping
    fun createProduct():RequestEntity<Unit>{
        TODO()
    }

    //물품수정(재고,설명) - 판매자만 가능
    @PutMapping("/{productId}")
    fun updateProduct():RequestEntity<Unit>{
        TODO()
    }

    //카테고리,이름,가격으로 검색 가능
    @GetMapping("/seach")
    fun searchProdcut():RequestEntity<Unit>{
        TODO()
    }
}

