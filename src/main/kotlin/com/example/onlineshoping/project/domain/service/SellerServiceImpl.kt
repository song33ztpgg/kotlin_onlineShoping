package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateSellerRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import com.example.onlineshoping.project.domain.dto.response.SellerResponse
import com.example.onlineshoping.project.domain.exception.InvalidCredentialException
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Seller
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.SellerRepository
import com.example.onlineshoping.project.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SellerServiceImpl(
    private val sellerRepository: SellerRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : SellerService {

    override fun signupSeller(request: CreateSellerRequest): SellerResponse {

         val seller = Seller(
            email = request.email,
            password = request.password,
            name = request.name,
            account = request.account
        )

        val saveSeller =  sellerRepository.save(seller)

        return saveSeller.toResponse()

    }


}