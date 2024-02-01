package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.CartResponse
import jakarta.persistence.*

@Entity
@Table
class Cart(

    @Column(name = "product_id")
    var product_id: Long,

    @Column(name = "member_id")
    var member_id: Long,

    @Column(name = "amount")
    var amount: Int,

//물건
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = 'product_id')


//    구매자
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buyer_id")
//    val buyer: Buyer,


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Cart.toResponse(): CartResponse {
    return CartResponse(
        id = id!!,
        memberId = member_id,
        productId = product_id,
        amount = amount
    )
}