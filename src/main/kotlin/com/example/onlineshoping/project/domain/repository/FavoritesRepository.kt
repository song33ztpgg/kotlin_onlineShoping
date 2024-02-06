package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Favorites
import org.springframework.data.jpa.repository.JpaRepository

interface FavoritesRepository:JpaRepository<Favorites,Long> {
    fun findAllByProductId(productId:Long):List<Favorites>
    fun countByProductId(productId: Long):Int
    fun findAllByMemberId(productId: Long):List<Favorites>
    fun findByMemberIdAndProductId(memberId :Long,productId: Long):Favorites?

}