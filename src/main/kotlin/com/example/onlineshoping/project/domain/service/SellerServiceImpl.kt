package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateSellerRequest
import com.example.onlineshoping.project.domain.dto.response.SellerResponse
import com.example.onlineshoping.project.domain.model.Seller
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.SellerRepository
import org.springframework.stereotype.Service

@Service
class SellerServiceImpl(
    private val sellerRepository: SellerRepository
):SellerService {

    override fun singupSeller(request: CreateSellerRequest): SellerResponse {
        return sellerRepository.save(
            Seller(
                name = request.name,
                password = request.password,
                seller_account = request.sellerAccount
            )
        ).toResponse()
    }


}