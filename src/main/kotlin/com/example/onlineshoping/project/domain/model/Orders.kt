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

    @Column(name = "member_id")
    var member_id: Long,

    @Column(name = "amount")
    var amount: Int,

    @Column(name = "status")
    var status: String,

    @Column(name = "order_date")
    var order_date: Date  =  Date(),

    @Column(name = "road_address")
    var road_address: String



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
        productId = product_id,
        memberId = member_id,
        amount = amount,
        status = status,
        orderDate = Date(),
        roadAddress = road_address
    )
}