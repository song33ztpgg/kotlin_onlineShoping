package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateBuyerRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.response.BuyerResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import com.example.onlineshoping.project.domain.exception.InvalidCredentialException
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Buyer
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.BuyerRepository
import com.example.onlineshoping.project.domain.repository.SellerRepository
import com.example.onlineshoping.project.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class BuyerServiceImpl(
    private val buyerRepository: BuyerRepository,
    private val sellerRepository: SellerRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : BuyerService {


    override fun login(request:LoginRequest):LoginResponse{
//        val findUser = buyerRepository.findByEmail(request.email) ?: throw ModelNotFoundException("Login",null)
        val findUser = buyerRepository.findByEmail(request.email)


//        if(!passwordEncoder.matches(request.password, findUser.password)){
//           throw InvalidCredentialException()
//       }

        if (findUser != null && passwordEncoder.matches(request.password, findUser.password)) {
            return LoginResponse(
                accessToken = jwtPlugin.generateAccessToken(
                    subject = findUser.id.toString(),
                    email =  findUser.email,
                    name  = findUser.name,
                    phoneNumber = findUser.phone_number?:"",
                    balance = findUser.balance?:0

                )
            )
        } else {
            val findUser2 = sellerRepository.findByEmail(request.email)?:throw ModelNotFoundException("Login",null)

            return LoginResponse(
                accessToken = jwtPlugin.generateAccessToken(
                    subject = findUser2.id.toString(),
                    email = findUser2.email,
                    name = findUser2.name,
                    account = findUser2.account
                )
            )
        }




    }

    override fun signupBuyer(request: CreateBuyerRequest): BuyerResponse {
        val buyer = Buyer(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            phone_number = "",
            balance = 0,
        )
        val savedBuyer = buyerRepository.save(buyer)
        return savedBuyer.toResponse()
    }
}