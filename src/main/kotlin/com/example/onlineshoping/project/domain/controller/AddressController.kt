package com.example.onlineshoping.project.domain.controller

import com.example.onlineshoping.project.domain.dto.request.CreateAddress
import com.example.onlineshoping.project.domain.dto.request.UpdateAddress
import com.example.onlineshoping.project.domain.dto.request.UpdateAddressDefault
import com.example.onlineshoping.project.domain.dto.response.AddressResponse
import com.example.onlineshoping.project.domain.service.AddressService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/address")
class AddressController(
    private val addressService: AddressService
) {

    //주소 불러오기
    @GetMapping
    fun getAddress(@AuthenticationPrincipal member :User):ResponseEntity<List<AddressResponse>>{
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(addressService.getAddress(memberId))
    }

    //주소 저장하기
    @PostMapping
    fun createAddress(@AuthenticationPrincipal member: User,@RequestBody createAddress: CreateAddress):ResponseEntity<AddressResponse>{
        val memberId = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(addressService.createAddress(memberId,createAddress))
    }

    //주소 메인 정하기
    @PutMapping("/isDefault")
    fun selectMainAddress(@AuthenticationPrincipal member:User,@RequestBody updateAddressDefault: UpdateAddressDefault) :ResponseEntity<AddressResponse>{
        val memberId  = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(addressService.selectMainAddress(memberId,updateAddressDefault))
    }

    //주소 수정하기
    @PutMapping("/update")
    fun updateAddress(@AuthenticationPrincipal member:User,@RequestBody updateAddress: UpdateAddress) :ResponseEntity<AddressResponse>{
        val memberId  = member.username.toLong()
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(addressService.updateAddress(memberId,updateAddress))
    }
}