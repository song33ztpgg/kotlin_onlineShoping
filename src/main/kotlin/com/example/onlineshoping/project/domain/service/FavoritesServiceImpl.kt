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
        val findByMember = favoritesRepository.findAllByMemberId(request.membeerId)
        val findByProduct = favoritesRepository.findAllByProductId(request.productId)

            val favorites = Favorites(
                productId = request.productId,
                memberId = request.membeerId
            )

            val updateFavorites = favoritesRepository.save(favorites)

            return updateFavorites.toResponse()
        }








    override fun viewAllmyFavoritesList(): List<FavoritesResponse> {
        return favoritesRepository.findAll().map { it.toResponse() }
    }
}