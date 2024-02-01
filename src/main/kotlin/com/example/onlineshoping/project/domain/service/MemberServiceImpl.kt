package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateMemberRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import com.example.onlineshoping.project.domain.exception.InvalidCredentialException
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Member
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.MemberRepository
import com.example.onlineshoping.project.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : MemberService {


    override fun login(request: LoginRequest): LoginResponse {

        val findUser = memberRepository.findByEmail(request.email) ?:throw ModelNotFoundException("login",null)

        if(!passwordEncoder.matches(request.password, findUser.password)){
           throw InvalidCredentialException()
       }


        val accessToken = jwtPlugin.generateAccessToken(
            subject = findUser.id.toString(),
            email =  findUser.email,
            name  = findUser.name,
            account = findUser.account,
            phoneNumber = findUser.phone_number,
            role = findUser.role
        )

        return  LoginResponse(accessToken)

    }

    override fun signupBuyer(request: CreateMemberRequest): MemberResponse {
        val member = Member(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            account = 0,
            phone_number = "",
            role = ""
        )
        val saveMember = memberRepository.save(member)
        return saveMember.toResponse()
    }
}