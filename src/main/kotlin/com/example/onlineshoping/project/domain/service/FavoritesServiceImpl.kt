package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateFavoritesRequest
import com.example.onlineshoping.project.domain.dto.response.FavoritesResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Favorites
import com.example.onlineshoping.project.domain.model.enum.DiscountStatus
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.FavoritesRepository
import com.example.onlineshoping.project.domain.repository.ProdcutRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class FavoritesServiceImpl(
    private val favoritesRepository: FavoritesRepository,
    private val productRepository: ProdcutRepository
):FavoritesService{


    //즐겨찾기 추가,제거
    override fun favorites(memberId :Long,request: CreateFavoritesRequest): String {

        val (productId) = request

        val result =  favoritesRepository.findByMemberIdAndProductId(memberId,productId)
        val findProduct = productRepository.findByIdOrNull(productId) ?:throw ModelNotFoundException("Product",productId)

        if(result == null) {
            val favorites = Favorites(
                productId = productId,
                memberId = memberId
            )

            favoritesRepository.save(favorites)

            findProduct.favoritesCount = favoritesRepository.countByProductId(productId)
            productRepository.save(findProduct).toResponse()

            return "즐겨찾기 추가가 되었습니다"
        } else {

            favoritesRepository.delete(result)

            findProduct.favoritesCount = favoritesRepository.countByProductId(productId)
            productRepository.save(findProduct).toResponse()


            return "즐겨찾기를 취소 하였습니다"
        }

        }

    //즐겨찾기 보기
    override fun viewAllmyFavoritesList(memberId: Long): List<FavoritesResponse> {
        val findMemberByFavorites = favoritesRepository.findAllByMemberId(memberId)
        val mappingFavorites = findMemberByFavorites.map { it.toResponse() }
        return mappingFavorites
    }


}
