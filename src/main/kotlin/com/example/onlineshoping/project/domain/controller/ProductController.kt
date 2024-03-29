package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateProduct
import com.example.onlineshoping.project.domain.dto.response.ProductResponse
import com.example.onlineshoping.project.domain.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.userdetails.User

@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    //상품제작, 판매자만 가능
    @PostMapping
    fun createProduct(
        @AuthenticationPrincipal member :User,
        @RequestBody createProductRequest: CreateProductRequest):ResponseEntity<ProductResponse>{

        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productService.createProduct(memberId,createProductRequest))
    }

    //카테고리로 검색 가능
    @GetMapping("/seach/{productName}")
    fun searchProducts(@PathVariable productName:String):ResponseEntity<List<ProductResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.searchProducts(productName))
    }

    //물품수정(재고,설명) - 판매자만 가능
    @PutMapping
    fun updateProduct(
        @AuthenticationPrincipal member: User,
        @RequestBody updateProduct: UpdateProduct):ResponseEntity<ProductResponse>{

        val memberId =  member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(productService.updateProduct(memberId,updateProduct))
    }



}




//  @PreAuthorize("#seller.name == 'aaa'")

//        val userPrincipal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
//        println("현재 사용자 정보: ${userPrincipal.email}, ${userPrincipal.name}, ${userPrincipal.phoneNumber}"