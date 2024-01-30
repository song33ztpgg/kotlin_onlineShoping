package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.ProductResponse
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
    var price:Long,

    @Column(name = "discount_type")
    var discount_type:String,

    @Column(name = "discount")
    var discount : Int,

    @Column(name = "product_info")
    var product_info:String,

    @Column(name = "remaining_stock")
    var remaining_stock:Int,

    @Column(name ="favorites_count")
    var favorites_count:Int

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

fun Product.toResponse():ProductResponse{
    return ProductResponse(
        id = id!!,
        sellerId = seller_id,
        category = category,
        name = name,
        price = price,
        discountType = discount_type,
        discount = discount,
        productInfo = product_info,
        remainingStock = remaining_stock,
        favoritesCount = favorites_count
    )
}