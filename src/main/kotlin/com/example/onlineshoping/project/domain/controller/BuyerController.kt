package com.example.onlineshoping.project.domain.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BuyerController() {

    @PostMapping("/buyers")
    fun singupBuyer(/*@RequestBody buyerResponse: BuyerResponse*/): RequestEntity<Unit> {
        TODO()
    }

    @PutMapping("/buyers/{buyerId}")
    fun updateUserInfo(
        @PathVariable buyerId: Long
    ):RequestEntity<Unit>{
        TODO()
    }
}