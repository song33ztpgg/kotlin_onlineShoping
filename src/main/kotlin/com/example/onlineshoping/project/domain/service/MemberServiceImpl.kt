package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateMemberRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateMemberRequest
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import com.example.onlineshoping.project.domain.exception.ErrorResponse
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


    //로그인
    override fun login(request: LoginRequest): LoginResponse {

        val findUser = memberRepository.findByEmail(request.email) ?: throw ModelNotFoundException("login", null)

        //수정할 에러 메세지
        if (!passwordEncoder.matches(request.password, findUser.password)) {
            throw InvalidCredentialException()
        }

        val accessToken = jwtPlugin.generateAccessToken(
            subject = findUser.id.toString(),
            email = findUser.email,
            role = findUser.role.name
        )

        return LoginResponse(accessToken)
    }

    //회원가입
    override fun signupMember(request: CreateMemberRequest): MemberResponse {
        val (email,password,name,phoneNumber,role) = request

        val findByEmail = memberRepository.findByEmail(email)


        //수정할 에러 메세지
        if(findByEmail != null) {
            throw ErrorResponse("중복된Email있습니다")
        }


        val createMember = Member(
            email = email,
            password = passwordEncoder.encode(password),
            name = name,
            account = 10000,
            phoneNumber = phoneNumber,

            role = when (role) {
                "seller" -> MemberRole.ROLE_SELLER
                "buyer" -> MemberRole.ROLE_BUYER
                //수정할 에러 메세지
                else -> throw IllegalArgumentException("Invalid role")
            }
        )

        val saveMember = memberRepository.save(createMember)
        val mappingSaveMember = saveMember.toResponse()
        return mappingSaveMember
    }



    //계좌 충전
    override fun updateMember(memberId: Long, request: UpdateMemberRequest): MemberResponse {
        val (account) = request
        val findMember = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", memberId)

        findMember.account += account

        val updateMember = memberRepository.save(findMember)
        val mappingUpdateMember = updateMember.toResponse()
        return mappingUpdateMember

    }

    //나의 정보보기
    override fun myPage(memberId: Long): MemberResponse {
        val memberInfo = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", memberId)
        val mappingMemberInfo = memberInfo.toResponse()
        return mappingMemberInfo
    }

}