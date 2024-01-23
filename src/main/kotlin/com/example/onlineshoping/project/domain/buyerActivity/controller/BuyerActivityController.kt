package com.example.onlineshoping.project.domain.buyerActivity.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("buyerActivity")
@RestController
class BuyerActivityController(){

    @PostMapping("/addCart")
    fun addCart():RequestEntity<Unit>{
        TODO()
    }

    @GetMapping("/viewCart")
    fun viewCart():RequestEntity<Unit>{
        TODO()
    }

    @PutMapping("/paymentCart")
    fun paymentCart():RequestEntity<Unit>{
        TODO()
    }

    @DeleteMapping("/deleteOrderCart")
    fun deleteOrderCart():RequestEntity<Unit>{
        TODO()
    }

    @DeleteMapping("/deleteCancelOrder")
    fun deleteCancelOrder():RequestEntity<Unit>{
        TODO()
    }

    @PutMapping("/favorites")
    fun favorites():RequestEntity<Unit>{
        TODO()
    }

    @GetMapping("viewAllmyFavoritesList")
    fun viewAllmyFavoritesList():RequestEntity<Unit>{
        TODO()
    }
}