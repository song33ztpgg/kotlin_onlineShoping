package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateMemberRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateMemberRequest
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import com.example.onlineshoping.project.domain.exception.InvalidCredentialException
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Member
import com.example.onlineshoping.project.domain.model.enum.MemberRole
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.MemberRepository
import com.example.onlineshoping.project.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : MemberService {


    override fun login(request: LoginRequest): LoginResponse {

        val findUser = memberRepository.findByEmail(request.email) ?: throw ModelNotFoundException("login", null)

        if (!passwordEncoder.matches(request.password, findUser.password)) {
            throw InvalidCredentialException()
        }

        val accessToken = jwtPlugin.generateAccessToken(
                subject = findUser.id.toString(),
                email = findUser.email,
                role = findUser.role.name
            )

        return LoginResponse(accessToken)

//        val accessToken = jwtPlugin.generateAccessToken(
//            subject = findUser.id.toString(),
//            email = findUser.email,
////            name = findUser.name,
////            account = findUser.account,
////            phoneNumber = findUser.phoneNumber,
//            role = findUser.role.na
//        )



    }

    override fun signupBuyer(request: CreateMemberRequest): MemberResponse {
        val member = Member(
            email = request.email,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            account = 10000,
            phoneNumber = request.phoneNumber,

            role = when (request.role) {
                "seller" -> MemberRole.seller
                "buyer" -> MemberRole.buyer
                else -> throw IllegalArgumentException("Invalid role")
            }
        )
        val saveMember = memberRepository.save(member)
        return saveMember.toResponse()
    }


    //계좌 충전
    override fun memberUpdate(request: UpdateMemberRequest): MemberResponse {
//        val findMember = memberRepository.findByIdOrNull(request.memberId) ?: throw ModelNotFoundException(
//            "Member",
//            request.memberId
//        )
//        findMember.account += request.account
//
//        val updateMember = memberRepository.save(findMember)
//        return updateMember.toResponse()

        TODO()
    }

    //나의 정보보기
    override fun mypage(memberId: Long): MemberResponse {
        val memberInfo = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", memberId)
        return memberInfo.toResponse()
    }
}