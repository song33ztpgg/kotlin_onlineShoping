package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateFavoritesRequest
import com.example.onlineshoping.project.domain.dto.response.FavoritesResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Favorites
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.FavoritesRepository
import com.example.onlineshoping.project.domain.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FavoritesServiceImpl(
    private val favoritesRepository: FavoritesRepository,
    private val productRepository: ProductRepository
) : FavoritesService {


    //즐겨찾기 추가,제거
    override fun favorites(memberId: Long, request: CreateFavoritesRequest): String {

        val (productId) = request

        val findFavorite = favoritesRepository.findByMemberIdAndProductId(memberId, productId)
        val findProduct =
            productRepository.findByIdOrNull(productId) ?: throw ModelNotFoundException("Product", productId)

        //기존 즐겨찾기 상태 여부에 따라 제작 및 삭제
        if (findFavorite == null) {

            val createFavorite = Favorites(
                productId = productId,
                memberId = memberId
            )

            favoritesRepository.save(createFavorite)

            //물건의 좋아요 수 업데이트
            findProduct.favoritesCount = favoritesRepository.countByProductId(productId)
            productRepository.save(findProduct).toResponse()

            return "즐겨찾기 추가가 되었습니다"
        } else {

            favoritesRepository.delete(findFavorite)

            findProduct.favoritesCount = favoritesRepository.countByProductId(productId)
            productRepository.save(findProduct).toResponse()

            return "즐겨찾기를 취소 하였습니다"
        }

    }

    //즐겨찾기 보기
    override fun viewAllFavoritesList(memberId: Long): List<FavoritesResponse> {
        val findAllMemberByFavorites = favoritesRepository.findAllByMemberId(memberId)
        val mappingFavorites = findAllMemberByFavorites.map { it.toResponse() }
        return mappingFavorites
    }


}
