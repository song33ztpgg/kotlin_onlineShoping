package com.example.onlineshoping.project.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
class Product(
    @Column(name = "seller_id")
    var seller_id:Long,

    @Column(name = "category")
    var category:String,

    @Column(name = "name")
    var name : String,

    @Column(name = "price")
    var price:Int,

    @Column(name = "discount_type")
    var discount_type:String,

    @Column(name = "discount")
    var discount : Int,

    @Column(name = "product_info")
    var product_info:String,

    @Column(name = "remaining_stock")
    var remaining_stock:Int

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}