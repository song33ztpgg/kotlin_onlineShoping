package com.example.onlineshoping.project.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val id: Long,
    val email: String,
    val name :String,
    val phoneNumber: String,
    val balance: Long,
    val authorities: Collection<GrantedAuthority>
) {

    constructor(id: Long, email: String, name:String, phoneNumber:String, balance:Long) : this(
        id,
        email,
        name,
        phoneNumber,
        balance,
        emptyList() // authorities 필드만 초기화
    )

    //추가된 부분
    constructor(id: Long, email: String, name:String, phoneNumber:String, balance:Long, roles: Set<String>) : this(
        id,
        email,
        name,
        phoneNumber,
        balance,
        roles.map { SimpleGrantedAuthority("ROLE_$it" ) }
    )


}
