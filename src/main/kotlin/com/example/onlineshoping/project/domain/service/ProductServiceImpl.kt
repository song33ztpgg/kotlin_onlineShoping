package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateProductRequest
import com.example.onlineshoping.project.domain.dto.response.ProductResponse
import com.example.onlineshoping.project.domain.model.Product
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.ProdcutRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl (
    private val prodcutRepository: ProdcutRepository
):ProductService{
    override fun searchProdcut(reqest: String): List<ProductResponse> {
        return prodcutRepository.findAll().map{it.toResponse()}
    }

    override fun createProduct(request: CreateProductRequest): ProductResponse {

        val product = Product(
            seller_id = request.sellerId,
            category = request.category,
            name = request.name,
            price = request.price,
            discount_type = request.discountType,
            discount = request.discount,
            product_info = request.productInfo,
            remaining_stock = request.remainingStock,
            favorites_count = 0
        )

        val productdata =prodcutRepository.save(product).toResponse()

        return productdata
//        return prodcutRepository.save(
//           Product(
//               seller_id = request.sellerId,
//               category = request.category,
//               name = request.name,
//               price = request.price,
//               discount_type = request.discountType,
//               discount = request.discount,
//               product_info = request.productInfo,
//               remaining_stock = request.remainingStock,
//               favorites_count = request.favoritesCount
//               )
//       ).toResponse()



    }

}