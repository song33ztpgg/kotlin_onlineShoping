package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateProduct
import com.example.onlineshoping.project.domain.dto.response.ProductResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Product
import com.example.onlineshoping.project.domain.model.enum.DiscountStatus
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.ProdcutRepository
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
    override fun createProduct(memberId:Long,request: CreateProductRequest): ProductResponse {

        val product = Product(
            memberId = memberId,
            category = request.category,
            name = request.name,
            price = request.price,
            discountType = DiscountStatus.originalPrice,
            discount =  0,
            product_info = request.productInfo,
            remainingStock = request.remainingStock,
            favoritesCount = 0
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
        findProduct.discountType = DiscountStatus.valueOf(discountType)
        findProduct.discount = discount
        findProduct.product_info = productInfo
        findProduct.remainingStock = remainingStock


        return prodcutRepository.save(findProduct).toResponse()


    }

}