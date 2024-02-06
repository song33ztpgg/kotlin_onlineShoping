package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.OrderResponse
import com.example.onlineshoping.project.domain.model.enum.DiscountStatus
import com.example.onlineshoping.project.domain.model.enum.OrdersStatus
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

    @Column(name = "productId")
    var productId: Long,

    @Column(name = "memberId")
    var memberId: Long,

    @Column(name = "amount")
    var amount: Int,

    @Column(name = "discountStatus")
    var discountStatus: DiscountStatus,

    @Column(name = "discount")
    var discount: Int,

    @Column(name = "status")
    var status: OrdersStatus,
    @Column(name = "orderDate")
    var orderDate: Date  =  Date(),


    @Column(name = "roadAddress")
    var roadAddress: String



    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "상대방_id")
//    val 상대방 모델 : 모델,

//    @OneToMany(mappedBy = "나의id (cart_id)", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    var 상대방모델이름: MutableList<모델이름> = mutableListOf()



) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Orders.toResponse(): OrderResponse {
    return OrderResponse(
        id = id!!,
        productId = productId,
        memberId = memberId,
        amount = amount,
        discountStatus = discountStatus.name,
        discount = discount,
        status = status.name,
        orderDate = Date(),

        roadAddress = roadAddress
    )
}