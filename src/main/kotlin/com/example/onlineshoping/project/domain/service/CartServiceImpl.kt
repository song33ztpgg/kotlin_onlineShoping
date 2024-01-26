package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.AddCartRequest
import com.example.onlineshoping.project.domain.dto.response.CartResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Cart
import com.example.onlineshoping.project.domain.model.CartStatus
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.CartRepository
import com.example.onlineshoping.project.domain.repository.OrderRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CartServiceImpl(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository,
    private val orderService: OrderService
) : CartService {


    //장바구니 전체 보기
    override fun viewCart(): List<CartResponse> {
        return cartRepository.findAll().map { it.toResponse() }
    }


    //장바구니에 담기
    override fun addCart(request: AddCartRequest): CartResponse {
       return  cartRepository.save(
            Cart(
                buyer_id = request.buyerId,
                product_id = request.productId,
                amount = request.amount,
                status = CartStatus.장바구니
            )
        ).toResponse()

    }

    //상품결재

    @Transactional
    override fun paymentCart(userId:Long) {
    println("확인")
//        val userCart = cartRepository.findByIdOrNull(userId) ?:throw ModelNotFoundException("Cart",userId)
//        val userCart = cartRepository.findAllByBuyerId(userId)
//            cartRepository.findAllByBuyerId(userId)
//        println(userCart[0])



//        for(u in userCart){
//            println(u.id+ "의 상품의 제고는 " + u.amount)
//        }
    }


    override fun deleteCancelOrder() {
        TODO("Not yet implemented")
    }






    override fun deleteCart() {
        TODO("Not yet implemented")
    }
}


