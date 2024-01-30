package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.response.ProductResponse

interface ProductService {
    fun searchProdcut(request:String):List<ProductResponse>

    fun createProduct(request: CreateProductRequest): ProductResponse
}