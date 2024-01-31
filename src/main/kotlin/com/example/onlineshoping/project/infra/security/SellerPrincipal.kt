package com.example.onlineshoping.project.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class SellerPrincipal(
    val id: Long,
    val email: String,
    val name: String,
    val account: Long?,
    val authorities: Collection<GrantedAuthority>
) {

    // Seller 생성자
    constructor(id: Long, email: String, name: String, account: Long) : this(
        id,
        email,
        name,
        account,
        emptyList() // authorities 필드만 초기화
    )

    // Seller 생성자 with roles
    constructor(id: Long, email: String, name: String, account: Long, roles: Set<String>) : this(
        id,
        email,
        name,
        account,
        roles.map { SimpleGrantedAuthority("ROLE_$it") }
    )



}
