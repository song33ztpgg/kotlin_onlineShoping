package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.BuyerResponse
import jakarta.persistence.*

@Entity
@Table
class Buyer(

    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "phone_number")
    var phone_number: String,

    @Column(name = "balance")
    var balance: Long,

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "상대방_id")
//    val 상대방 모델 : 모델,

//    주소,
//    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    var address: MutableList<Address> = mutableListOf(),

//    ,카트,
//    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    var cart: MutableList<Cart> = mutableListOf(),

//    // 즐겨찾기
//    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    var favorites:MutableList<Favorites> = mutableListOf()


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}


fun Buyer.toResponse(): BuyerResponse {
    return BuyerResponse(
        id = id!!,
        email = email,
        password = password,
        name = name,
        phoneNumber = phone_number,
        balance = balance
    )

}