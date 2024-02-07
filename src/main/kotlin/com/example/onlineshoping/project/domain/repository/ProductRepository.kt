package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository:JpaRepository<Product,Long> {
    fun findByCategory(categoryName: String): List<Product>
}