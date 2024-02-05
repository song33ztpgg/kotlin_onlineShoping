package com.example.onlineshoping.project.domain.service


import com.example.onlineshoping.project.domain.dto.request.CreateMemberRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateMemberRequest
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse

interface MemberService {

    fun login(request: LoginRequest): LoginResponse

    fun signupBuyer(request: CreateMemberRequest): MemberResponse

    fun memberUpdate(request:UpdateMemberRequest):MemberResponse

    fun mypage(memberId:Long):MemberResponse
}