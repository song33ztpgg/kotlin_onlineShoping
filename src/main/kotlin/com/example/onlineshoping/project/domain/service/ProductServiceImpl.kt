package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateProduct
import com.example.onlineshoping.project.domain.dto.response.ProductResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Product
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.ProdcutRepository
import com.example.onlineshoping.project.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl (
    private val prodcutRepository: ProdcutRepository
):ProductService{
    //뭎품 검색
    override fun searchProdcut(reqest: String): List<ProductResponse> {
        var findProducts = prodcutRepository.findByCategory(reqest) ?:throw ModelNotFoundException("login",null)
        return findProducts.map { it.toResponse() }.toList()
    }

    //물품 제작
    override fun createProduct(request: CreateProductRequest): ProductResponse {

        val product = Product(
            member_id = request.memberId,
            category = request.category,
            name = request.name,
            price = request.price,
            discount_type = request.discountType,
            discount = request.discount,
            product_info = request.productInfo,
            remaining_stock = request.remainingStock,
            favorites_count = 0
        )

        val savedProduct = prodcutRepository.save(product)
        return savedProduct.toResponse()
    }

    //물품 수정
    override fun updateProduct(request: UpdateProduct): ProductResponse {

        val(productId,category,name,price,discountType,discount,productInfo,remainingStock) = request
        var findProduct = prodcutRepository.findByIdOrNull(productId) ?:throw ModelNotFoundException("Product",productId)

        findProduct.category = category
        findProduct.name = name
        findProduct.price = price
        findProduct.discount_type = discountType
        findProduct.discount = discount
        findProduct.product_info = productInfo
        findProduct.remaining_stock = remainingStock


        return prodcutRepository.save(findProduct).toResponse()


    }

}