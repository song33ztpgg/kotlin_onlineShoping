package com.example.onlineshoping.project.domain.model

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
    @Column(name = "buyer_id")
    var buyer_id:Long,

    @Column(name = "product_id")
    var product_id: Long,

    @Column(name = "amount")
    var amount:Int,

    @Column(name = "status")
    var status:String,

    @Column(name ="order_date")
    var order_date :Date

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}