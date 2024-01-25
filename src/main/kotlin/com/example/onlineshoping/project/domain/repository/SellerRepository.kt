package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Seller
import org.springframework.data.jpa.repository.JpaRepository

interface SellerRepository:JpaRepository<Seller,Long> {
}