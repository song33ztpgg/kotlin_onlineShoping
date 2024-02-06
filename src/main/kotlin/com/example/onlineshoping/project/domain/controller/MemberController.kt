package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateMemberRequest
import com.example.onlineshoping.project.domain.dto.request.LoginRequest
import com.example.onlineshoping.project.domain.dto.request.UpdateMemberRequest
import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.dto.response.LoginResponse
import org.springframework.security.core.userdetails.User

import com.example.onlineshoping.project.domain.model.Member
import com.example.onlineshoping.project.domain.service.MemberService
import com.example.onlineshoping.project.infra.security.jwt.JwtPlugin
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
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
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
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
    fun updateMember(
        @RequestBody updateMemberRequest: UpdateMemberRequest,
        @AuthenticationPrincipal member: User
    ): ResponseEntity<MemberResponse> {

        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.memberUpdate(memberId, updateMemberRequest))
    }

    //나의 정보 보가
    @GetMapping("/member")
    fun mypage(@AuthenticationPrincipal member: User): ResponseEntity<MemberResponse> {
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.mypage(memberId))
    }

//    @GetMapping("/test1")
//    fun test(@AuthenticationPrincipal user: org.springframework.security.core.userdetails.User) {
//        println( user.username.toLong())
//        println(user.authorities)
//        if(user.authorities.toString() == "[ROLE_BUYER]") {
//            println("buyer")
//        } else if(user.authorities.toString() == "[ROLE_SELLER]") {
//            println("seller")
//        }
//        when(user.authorities.toString()){
//            "[ROLE_BUYER]" -> println("buyer")
//            "[ROLE_SELLER]" -> println("seller")
//            else -> println("xxx")
//        }
//    }


//
//
//    //    @PreAuthorize("hasRole('seller')")
//    @GetMapping("/test2")
//    @PreAuthorize("hasRole('ROLE_SELLER')")
//    fun test2(test2 :UsernamePasswordAuthenticationToken){
//       val name = test2.name
//        println(name)
//    }
//    UsernamePasswordAuthenticationToken

//    @PostMapping("/test3")
//    fun test3(@RequestBody test:String):ResponseEntity<String>{
//        return ResponseEntity.status(HttpStatus.OK).body(memberService.testPage(test))
//    }


}