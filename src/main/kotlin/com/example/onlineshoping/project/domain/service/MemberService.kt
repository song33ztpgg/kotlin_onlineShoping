package com.example.onlineshoping.project.domain.service


import com.example.onlineshoping.project.domain.dto.request.CreateMemberRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateMemberRequest
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse

interface MemberService {

    fun login(loginRequest: LoginRequest): LoginResponse

    fun signupMember(createMemberRequest: CreateMemberRequest): MemberResponse

    fun updateMember(memberId:Long,updateMemberRequest:UpdateMemberRequest):MemberResponse

    fun myPage(memberId:Long):MemberResponse

}