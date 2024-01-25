package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateFavoritesRequest
import com.example.onlineshoping.project.domain.dto.response.FavoritesResponse
import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("favorites")
class FavoritesController {


//    즐겨찾기 추가
    @PostMapping
    fun favorites( @RequestBody createFavoritesRequest: CreateFavoritesRequest): RequestEntity<FavoritesResponse> {
        TODO()
    }

//    즐겨찾기 목록
    @GetMapping
    fun viewAllmyFavoritesList(): RequestEntity<FavoritesResponse> {
        TODO()
    }
}