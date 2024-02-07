package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateProduct
import com.example.onlineshoping.project.domain.dto.response.ProductResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Product
import com.example.onlineshoping.project.domain.model.enum.DiscountStatus
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.FavoritesRepository
import com.example.onlineshoping.project.domain.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl (
    private val productRepository: ProductRepository,
    private val favoritesRepository: FavoritesRepository
):ProductService{

    //물품 제작
    override fun createProduct(memberId:Long,request: CreateProductRequest): ProductResponse {

        val (category,name,price,productInfo,remainingStock) = request

        val createProduct = Product(
            memberId = memberId,
            category = category,
            name = name,
            price = price,
            discountType = DiscountStatus.originalPrice,
            discount =  0,
            product_info = productInfo,
            remainingStock = remainingStock,
            favoritesCount = 0
        )

        val savedProduct = productRepository.save(createProduct)
        val mappingSaveProduct = savedProduct.toResponse()
        return mappingSaveProduct
    }

    //뭎품 검색
    override fun searchProducts(productName: String): List<ProductResponse> {
        val findProducts = productRepository.findByCategory(productName)
        val mappingFindProducts = findProducts.map { it.toResponse() }
        return mappingFindProducts
    }

    //물품 수정
    override fun updateProduct(memberId :Long, request: UpdateProduct): ProductResponse {

        val(productId,category,name,price,discountType,discount,productInfo,remainingStock) = request
        val findProduct = productRepository.findByIdOrNull(productId) ?:throw ModelNotFoundException("Product",productId)

        findProduct.category = category
        findProduct.name = name
        findProduct.price = price
        findProduct.discountType = DiscountStatus.valueOf(discountType)
        findProduct.discount = discount
        findProduct.product_info = productInfo
        findProduct.remainingStock = remainingStock

        val updateProduct  = productRepository.save(findProduct)
        val mappingUpdateProduct = updateProduct.toResponse()
        return mappingUpdateProduct
    }

}