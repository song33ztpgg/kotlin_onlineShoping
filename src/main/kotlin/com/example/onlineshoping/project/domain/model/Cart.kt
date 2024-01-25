package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.CartResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
class Cart(
    @Column(name = "buyer_id")
    var buyer_id:Long,

    @Column(name = "product_id")
    var product_id: Long,

    @Column(name = "amount")
    var amount:Int,

    @Column(name = "status")
    var status:String

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
        amount  = amount,
        status = status
    )
}