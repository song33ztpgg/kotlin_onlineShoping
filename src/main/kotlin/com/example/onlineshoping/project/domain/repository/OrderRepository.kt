package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Orders
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository:JpaRepository<Orders,Long> {
}