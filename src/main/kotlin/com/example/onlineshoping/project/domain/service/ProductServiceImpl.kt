package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.response.ProductResponse
import com.example.onlineshoping.project.domain.model.Product
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.OrderRepository
import com.example.onlineshoping.project.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl (
    private val productRepository: ProductRepository
):ProductService{
    override fun searchProdcut(reqest: String): List<ProductResponse> {
//        val product = productRepository.findByCategory(request)
//        return favoritesRepository.findAll().map { it.toResponse() }
        return productRepository.findAll().map { it.toResponse() }
    }

    override fun createProduct(request: CreateProductRequest): ProductResponse {
       return productRepository.save(
           Product(
               seller_id = request.sellerId,
               category = request.category,
               name = request.name,
               price = request.price,
               discount_type = request.discountType,
               discount = request.discount,
               product_info = request.productInfo,
               remaining_stock = request.remainingStock
               )
       ).toResponse()
    }

}