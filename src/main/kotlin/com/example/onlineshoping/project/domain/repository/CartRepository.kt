package com.example.onlineshoping.project.domain.repository


import com.example.onlineshoping.project.domain.model.Cart
import com.example.onlineshoping.project.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, Long> {
//    fun findAllByAmount(memberId :Long) : List<Cart>
    fun findAllByMemberId(memberId: Long) :List<Cart>


}