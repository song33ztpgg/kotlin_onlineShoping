package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository:JpaRepository<Address,Long> {
    fun findAllByMemberId(memberId:Long) :List<Address>
    fun findByMemberIdAndAddressDefault(memberId: Long,isAddressDefault: Boolean):Address?
}