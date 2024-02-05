package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateMemberRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateMemberRequest
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import com.example.onlineshoping.project.domain.model.Member
import com.example.onlineshoping.project.domain.service.MemberService
import com.example.onlineshoping.project.infra.security.jwt.JwtPlugin
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class MemberController(
   private val memberService: MemberService
) {



    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest) :ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.login(loginRequest))
    }


    @PostMapping("/signup")
    fun singupBuyer(@RequestBody createBuyerRequest: CreateMemberRequest): ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.signupBuyer(createBuyerRequest))
    }

    //금액 충전
    @PutMapping("/member/chargeAccount")
    fun updateMember(@RequestBody updateMemberRequest: UpdateMemberRequest):ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.memberUpdate(updateMemberRequest))
    }

    @GetMapping("/member/{memberId}")
    fun mypage(@PathVariable memberId:Long):ResponseEntity<MemberResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.mypage(memberId))
    }

    @GetMapping("/tets")
    fun test(@AuthenticationPrincipal user : org.springframework.security.core.userdetails.User) {
//        val userId = user.username.toLong()
        println("==============  check================")
    }


//    @GetMapping("/myEndpoint")
//    fun myEndpoint(request: HttpServletRequest) {
//        val jwtToken = request.
//
//    }
}