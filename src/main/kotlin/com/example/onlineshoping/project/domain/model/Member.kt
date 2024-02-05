package com.example.onlineshoping.project.domain.model

import com.example.onlineshoping.project.domain.dto.response.MemberResponse
import com.example.onlineshoping.project.domain.model.enum.MemberRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table
     class Member(
        @Column(name = "email")
        var email: String,

        @Column(name = "password")
        var password: String,

        @Column(name = "name")
        var name: String,

        @Column(name = "account")
        var account: Long,

        @Column(name = "phoneNumber")
        var phoneNumber: String,

        @Column(name = "role")
        var role: MemberRole

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "buyer_id")
//    val buyer: Buyer

// 구매자
//    @OneToMany(mappedBy = "나의id (cart_id)", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    var 상대방모델이름: MutableList<모델이름> = mutableListOf()


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Member.toResponse():MemberResponse{
    return MemberResponse(
        id = id!!,
        email = email,
        password = password,
        name = name,
        account = account,
        phoneNumber = phoneNumber,
        role = role.name
    )
}