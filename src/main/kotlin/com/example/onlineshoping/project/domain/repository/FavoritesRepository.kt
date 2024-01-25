package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Favorites
import org.springframework.data.jpa.repository.JpaRepository

interface FavoritesRepository:JpaRepository<Favorites,Long> {
}