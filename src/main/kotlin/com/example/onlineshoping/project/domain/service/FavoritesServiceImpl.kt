package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateFavoritesRequest
import com.example.onlineshoping.project.domain.dto.response.FavoritesResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Favorites
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.FavoritesRepository
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class FavoritesServiceImpl(
    private val favoritesRepository: FavoritesRepository
):FavoritesService{


    //즐겨찾기 추가,제거
    override fun favorites(memberId :Long,request: CreateFavoritesRequest): String {

        val (productId) = request

        val result =  favoritesRepository.findByMemberIdAndProductId(memberId,productId)

        if(result == null) {
            val favorites = Favorites(
                productId = productId,
                memberId = memberId
            )

            favoritesRepository.save(favorites)

            return "즐겨찾기 추가가 되었습니다"
        } else {

            favoritesRepository.delete(result)
            return "즐겨찾기를 취소 하였습니다"
        }

        }



    //즐겨찾기 보기
    override fun viewAllmyFavoritesList(memberId: Long): List<FavoritesResponse> {
        val findMemberByFavorites = favoritesRepository.findAllByMemberId(memberId)
        val favoritesMapping = findMemberByFavorites.map { it.toResponse() }
        return favoritesMapping
    }


}
