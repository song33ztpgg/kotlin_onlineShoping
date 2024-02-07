package com.example.onlineshoping.project.domain.service

import com.example.onlineshoping.project.domain.dto.request.CreateAddress
import com.example.onlineshoping.project.domain.dto.request.UpdateAddress
import com.example.onlineshoping.project.domain.dto.request.UpdateAddressDefault
import com.example.onlineshoping.project.domain.dto.response.AddressResponse
import com.example.onlineshoping.project.domain.exception.ErrorResponse
import com.example.onlineshoping.project.domain.exception.ModelNotFoundException
import com.example.onlineshoping.project.domain.model.Address
import com.example.onlineshoping.project.domain.model.toResponse
import com.example.onlineshoping.project.domain.repository.AddressRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl(
    private val addressRepository: AddressRepository
) : AddressService {
    override fun getAddress(memberId: Long): List<AddressResponse> {
        val findMyAddress = addressRepository.findAllByMemberId(memberId)
        val mappingAddress = findMyAddress.map { it.toResponse() }
        return mappingAddress
    }

    override fun createAddress(memberId: Long, request: CreateAddress): AddressResponse {
        val (roadAddress, addressDefault) = request

        val createAddress = Address(
            memberId = memberId,
            roadAddress = roadAddress,
            addressDefault = addressDefault
        )
        val findMymainAddress = addressRepository.findByMemberIdAndAddressDefault(memberId, true)

        if (findMymainAddress != null && addressDefault == true) {
            findMymainAddress.addressDefault = false
            addressRepository.save(findMymainAddress)
        }

        val mappingAddress = addressRepository.save(createAddress).toResponse()

        return mappingAddress
    }

    override fun updateAddress(memberId: Long, request: UpdateAddress): AddressResponse {
        val (addressId, roadAddress) = request

        val findAddress = addressRepository.findByIdOrNull(addressId) ?: throw ModelNotFoundException("Address", addressId)

        if (findAddress.memberId != memberId) {
            throw ErrorResponse("잘못된 주소를 선택하였습니다")
        }

        findAddress.roadAddress = roadAddress

        val updateAddress = addressRepository.save(findAddress)

        return updateAddress.toResponse()

    }


    override fun selectMainAddress(memberId: Long, request: UpdateAddressDefault): AddressResponse {
        val (addressId,addressDefault) = request

        val findAddress =
            addressRepository.findByIdOrNull(addressId) ?: throw ModelNotFoundException("Address", addressId)
        val findMyAddress = addressRepository.findAllByMemberId(memberId)


        if (findAddress.memberId != memberId) {
            throw ErrorResponse("잘못된 주소를 선택하였습니다")
        }

        if (findAddress.addressDefault == true) {
            findAddress.addressDefault = false

            val updateAddress = addressRepository.save(findAddress)
            val mappingUpdateAddress = updateAddress.toResponse()
            return mappingUpdateAddress

        } else {
            val existingTrueAddress = addressRepository.findByMemberIdAndAddressDefault(memberId, true)

            if (existingTrueAddress != null) {
                existingTrueAddress.addressDefault = false
                addressRepository.save(existingTrueAddress)
            }

            findAddress.addressDefault = true

            val updateAddress = addressRepository.save(findAddress)
            val mappingUpdateAddress = updateAddress.toResponse()
            return mappingUpdateAddress


        }

    }

}