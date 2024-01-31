package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.response.ProductResponse
import com.example.onlineshoping.project.domain.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(
    private val productService: ProductService
) {

    //상품제작, 판매자만 가능
//  @PreAuthorize("#seller.name == 'aaa'")
    @PostMapping
    fun createProduct(@RequestBody createProductRequest: CreateProductRequest):ResponseEntity<ProductResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productService.createProduct(createProductRequest))
    }

    //물품수정(재고,설명) - 판매자만 가능
    @PutMapping("/{productId}")
    fun updateProduct():ResponseEntity<Unit>{
        TODO()
    }



    //카테고리,이름,가격으로 검색 가능
    @GetMapping("/seach/{name}")
    fun searchProdcut(@PathVariable name:String):ResponseEntity<List<ProductResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(productService.searchProdcut(name))
    }
}

