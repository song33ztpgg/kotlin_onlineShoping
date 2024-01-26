package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.CartResponse
import jakarta.persistence.*

@Entity
@Table
class Cart(
    @Column(name = "product_id")
    var product_id: Long,

    @Column(name = "buyer_id")
    var buyer_id:Long,

    @Column(name = "amount")
    var amount:Int,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Cart.toResponse():CartResponse {
    return CartResponse(
        id = id!!,
        buyerId = buyer_id,
        productId = product_id,
        amount  = amount
    )
}