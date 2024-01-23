package com.example.onlineshoping.project.domain.controller

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("seleHistory")
class SaleHistoryController() {


    //판매 기록
    @GetMapping
    fun viewSaleHistory():RequestEntity<Unit>{
        TODO()
    }

    //결재 대기후 판맥자가 결재완료로 수정
    @PutMapping
    fun updateSaleHistory():RequestEntity<Unit>{
        TODO()
    }


}