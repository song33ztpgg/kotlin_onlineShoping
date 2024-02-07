package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateAddress
import com.example.onlineshoping.project.domain.dto.request.UpdateAddress
import com.example.onlineshoping.project.domain.dto.request.UpdateAddressDefault
import com.example.onlineshoping.project.domain.dto.response.AddressResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping

interface AddressService {


    fun getAddress(memberId: Long): List<AddressResponse>

    //주소 저장하기
    fun createAddress(memberId: Long, createAddress: CreateAddress): AddressResponse


    //주소 메인 정하기
    fun selectMainAddress(memberId: Long, updateAddressDefault: UpdateAddressDefault): AddressResponse

    //주소 수정하기
    fun updateAddress(memberId: Long, updateAddress: UpdateAddress): AddressResponse
}