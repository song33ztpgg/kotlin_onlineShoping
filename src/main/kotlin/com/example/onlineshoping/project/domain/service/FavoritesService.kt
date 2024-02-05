package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateFavoritesRequest
import com.example.onlineshoping.project.domain.dto.response.FavoritesResponse

interface FavoritesService {
    fun favorites(request:CreateFavoritesRequest):FavoritesResponse
    fun viewAllmyFavoritesList():List<FavoritesResponse>
}