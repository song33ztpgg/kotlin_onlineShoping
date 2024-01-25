package com.example.onlineshoping.project.domain.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

class Seller(
    @Column(name = "password")
    var password: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "seller_accoount")
    var seller_account:Int

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}