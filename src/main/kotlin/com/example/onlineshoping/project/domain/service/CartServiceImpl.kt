package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse
import com.example.onlineshoping.project.domain.model.Cart
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.CartRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository
) : CartService {


    override fun viewCart(): List<CartResponse> {
        return cartRepository.findAll().map { it.toResponse() }
    }


    override fun addCart(request: AddCartRequest): CartResponse {
       return  cartRepository.save(
            Cart(
                buyer_id = request.buyerId,
                product_id = request.productId,
                amount = request.amount,
                status = request.status
            )
        ).toResponse()

    }

    override fun deleteCancelOrder() {
        TODO("Not yet implemented")
    }

    override fun paymentCart() {
        TODO("Not yet implemented")
    }




    override fun deleteCart() {
        TODO("Not yet implemented")
    }
}