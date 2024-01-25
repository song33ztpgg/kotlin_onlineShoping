package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateBuyerRequest
import com.example.onlineshoping.project.domain.dto.response.BuyerResponse
import com.example.onlineshoping.project.domain.model.Buyer
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.BuyerRepository
import org.springframework.stereotype.Service


@Service
class BuyerServiceImpl(
    private val buyerRepository: BuyerRepository
):BuyerService {

    override fun singupBuyer(request:CreateBuyerRequest):BuyerResponse {
        return buyerRepository.save(
            Buyer(
                password =  request.password,
                buyer_balance = request.buyerBalance,
                phone_number = request.phoneNumber,
                name = request.name,
                address_type = request.addressType
            )
        ).toResponse()

    }
}