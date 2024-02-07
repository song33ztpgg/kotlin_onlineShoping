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

    //주소 가져오기
    override fun getAddress(memberId: Long): List<AddressResponse> {
        val findMyAddress = addressRepository.findAllByMemberId(memberId)
        val mappingAddress = findMyAddress.map { it.toResponse() }
        return mappingAddress
    }

    //주소 만들기
    override fun createAddress(memberId: Long, request: CreateAddress): AddressResponse {
        val (roadAddress, addressDefault) = request

        val createAddress = Address(
            memberId = memberId,
            roadAddress = roadAddress,
            addressDefault = addressDefault
        )

        //기본주소가 true인 주소 찾기
        val existingTrueAddress = addressRepository.findByMemberIdAndAddressDefault(memberId, true)

        if (existingTrueAddress != null && addressDefault == true) {
            existingTrueAddress.addressDefault = false
            addressRepository.save(existingTrueAddress)
        }

        val saveAddress = addressRepository.save(createAddress)
        val mappingCreateAddress = saveAddress.toResponse()
        return mappingCreateAddress
    }

    override fun updateAddress(memberId: Long, request: UpdateAddress): AddressResponse {
        val (addressId, roadAddress) = request

        val findAddress = addressRepository.findByIdOrNull(addressId) ?: throw ModelNotFoundException("Address", addressId)

        //수정할 에러 메세지
        if (findAddress.memberId != memberId) {
            throw ErrorResponse("잘못된 주소를 선택하였습니다")
        }

        findAddress.roadAddress = roadAddress

        val updateAddress = addressRepository.save(findAddress)
        val mappingUpdateAddress = updateAddress.toResponse()
        return mappingUpdateAddress

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