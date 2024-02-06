package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateFavoritesRequest
import com.example.onlineshoping.project.domain.dto.response.FavoritesResponse

interface FavoritesService {
    fun favorites(memberId :Long,request:CreateFavoritesRequest):String
    fun viewAllmyFavoritesList(memberId: Long):List<FavoritesResponse>
}