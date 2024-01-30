package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository:JpaRepository<Cart,Long> {
//    fun findAllByBuyerId(buyer_id:Long):List<Cart>
}