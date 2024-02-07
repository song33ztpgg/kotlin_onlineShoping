package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateProduct
import com.example.onlineshoping.project.domain.dto.response.ProductResponse

interface ProductService {

    fun createProduct(memberId:Long ,createProductRequest: CreateProductRequest): ProductResponse
    fun searchProducts(productName:String):List<ProductResponse>
    fun updateProduct(memberId:Long, updateProduct: UpdateProduct):ProductResponse
}