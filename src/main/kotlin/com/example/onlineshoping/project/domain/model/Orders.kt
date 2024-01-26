package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table
class Orders(

    @Column(name = "product_id")
    var product_id: Long,

    @Column(name = "buyer_id")
    var buyer_id: Long,

    @Column(name = "amount")
    var amount: Int,

    @Column(name = "status")
    var status: String,

    @Column(name = "order_date")
    var order_date: Date  =  Date()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Orders.toResponse(): OrderResponse {
    return OrderResponse(
        id = id!!,
        productId = product_id,
        buyerId = buyer_id,
        amount = amount,
        status = status,
        orderDate = order_date
    )
}