package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.AddressResponse
import jakarta.persistence.*

@Entity
@Table
class Address (
    @Column(name = "memberId")
    var memberId: Long,

    @Column(name = "roadAddress")
    var roadAddress: String,

    @Column(name = "addressDefault")
    var addressDefault: Boolean,




//    @Column(name = "member_id")
//    var member_id: String,
//
//    @Column(name = "road_address")
//    var road_address: String,
//
//    @Column(name = "is_default")
//    var is_default: String,




//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buyer_id")
//    val buyer: Buyer

// 구매자
//    @OneToMany(mappedBy = "나의id (cart_id)", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    var 상대방모델이름: MutableList<모델이름> = mutableListOf()


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Address.toResponse():AddressResponse{
    return AddressResponse(
        id = id!!,
        memberId = memberId,
        roadAddress = roadAddress,
        addressDefault = addressDefault
    )
}