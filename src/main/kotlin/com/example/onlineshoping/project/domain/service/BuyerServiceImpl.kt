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
import com.example.onlineshoping.project.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class BuyerServiceImpl(
    private val buyerRepository: BuyerRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : BuyerService {

//    override fun login(request: LoginRequest): LoginResponse {
//        val findUser = buyerRepository.findByEmail(request.email) ?:ModelNotFoundException("Login",null)
//
//       if(!passwordEncoder.matches(request.password, findUser.password)){
//           throw InvalidCredentialException()
//       }
//
//
//        return LoginResponse(
//            accessToken = jwtPlugin.generateAccessToken(
//                subject = "subject 입니다",
//                role = "롤입니다"
//            )
//        )
//
//    }


    override fun login(request:LoginRequest):LoginResponse{
        val findUser = buyerRepository.findByEmail(request.email) ?: throw ModelNotFoundException("Login",null)

        if(!passwordEncoder.matches(request.password, findUser.password)){
           throw InvalidCredentialException()
       }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = findUser.id.toString(),
                email =  findUser.email,
                name  = findUser.name,
                phoneNumber = findUser.phone_number,
                balance = findUser.balance

            )
        )



    }

    override fun singupBuyer(request: CreateBuyerRequest): BuyerResponse {
        return buyerRepository.save(
            Buyer(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                name = request.name,
                phone_number = request.phoneNumber,
                balance = request.balance,
            )
        ).toResponse()

    }
}