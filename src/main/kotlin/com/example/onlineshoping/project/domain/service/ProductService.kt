package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateProduct
import com.example.onlineshoping.project.domain.dto.response.ProductResponse

interface ProductService {
    fun searchProdcut(request:String):List<ProductResponse>

    fun createProduct(memberId:Long ,request: CreateProductRequest): ProductResponse

    fun updateProduct(request: UpdateProduct):ProductResponse
}