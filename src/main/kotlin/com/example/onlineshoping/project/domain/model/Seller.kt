package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.SellerResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
class Seller(
    @Column(name = "password")
    var password: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "seller_account")
    var seller_account:Long

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Seller.toResponse():SellerResponse{
    return SellerResponse(
        id = id!!,
        name = name,
        password = password,
        sellerAccount =  seller_account
    )
}