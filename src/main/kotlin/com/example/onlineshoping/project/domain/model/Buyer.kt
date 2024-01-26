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