package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateMemberRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateMemberRequest
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import org.springframework.security.core.userdetails.User
import com.example.onlineshoping.project.domain.repository.MemberRepository
import com.example.onlineshoping.project.domain.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class MemberController(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {

    //회원가입
    @PostMapping("/signup")
    fun signupBuyer(@RequestBody createBuyerRequest: CreateMemberRequest): ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.signupMember(createBuyerRequest))
    }

    //로그인
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.login(loginRequest))
    }

    //나의 정보 보가
    @GetMapping("/member")
    fun mypage(@AuthenticationPrincipal member: User): ResponseEntity<MemberResponse> {
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.myPage(memberId))
    }

    //금액 충전
    @PutMapping("/member/chargeAccount")
    fun updateMember(
        @AuthenticationPrincipal member: User,
        @RequestBody updateMemberRequest: UpdateMemberRequest): ResponseEntity<MemberResponse> {
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.updateMember(memberId, updateMemberRequest))
    }



}