package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateFavoritesRequest
import com.example.onlineshoping.project.domain.dto.response.FavoritesResponse
import com.example.onlineshoping.project.domain.model.Favorites
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.FavoritesRepository
import org.springframework.stereotype.Service

@Service
class FavoritesServiceImpl(
    private val favoritesRepository: FavoritesRepository
):FavoritesService{


    override fun favorites(request: CreateFavoritesRequest): FavoritesResponse {
        return favoritesRepository.save(
            Favorites(
                product_id = request.productId,
                member_id = request.membeerId
            )
        ).toResponse()
    }
    override fun viewAllmyFavoritesList(): List<FavoritesResponse> {
        return favoritesRepository.findAll().map { it.toResponse() }
    }
}