package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateFavoritesRequest
import com.example.onlineshoping.project.domain.dto.response.FavoritesResponse
import com.example.onlineshoping.project.domain.service.FavoritesService
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("favorites")
class FavoritesController(
    private val favoritesService: FavoritesService
) {


//    즐겨찾기 추가
    @PostMapping
    fun favorites(@AuthenticationPrincipal member:User,@RequestBody createFavoritesRequest: CreateFavoritesRequest): ResponseEntity<String> {
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(favoritesService.favorites(memberId,createFavoritesRequest))
    }

//    즐겨찾기 목록
    @GetMapping
    fun viewAllmyFavoritesList(@AuthenticationPrincipal member:User): ResponseEntity<List<FavoritesResponse>> {
        val memberId = member.username.toLong()
        return  ResponseEntity
            .status(HttpStatus.OK)
            .body(favoritesService.viewAllmyFavoritesList(memberId))
    }
}