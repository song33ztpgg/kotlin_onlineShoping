package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.BuyerResponse
import jakarta.persistence.*

@Entity
@Table
class Buyer(
    @Column(name = "password")
    var password: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "phone_number")
    var phone_number: String,

    @Column(name = "address_type")
    var address_type: String,

    @Column(name = "buyer_balance")
    var buyer_balance: Int,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}


fun Buyer.toResponse(): BuyerResponse {
    return BuyerResponse(
        id = id!!,
        name = name,
        password = password,
        phoneNumber = phone_number,
        addressType = address_type,
        buyerBalance = buyer_balance
        //        phone_number =  phone_number,
//        address_type = address_type,
//        buyer_balance  = buyer_balance

    )

}