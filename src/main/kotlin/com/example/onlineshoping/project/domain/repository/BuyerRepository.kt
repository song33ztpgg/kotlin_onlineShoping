package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Buyer
import org.springframework.data.jpa.repository.JpaRepository

interface BuyerRepository:JpaRepository<Buyer,Long> {
    fun findByEmail(email: String): Buyer?
}