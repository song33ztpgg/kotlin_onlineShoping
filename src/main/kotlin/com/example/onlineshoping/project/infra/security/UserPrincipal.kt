package com.example.onlineshoping.project.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority


data class UserPrincipal(
    val id: Long,
    val email: String,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(id: Long, email: String, roles: Set<String>) : this(
        id,
        email,
        roles.map { SimpleGrantedAuthority("ROLE_$it" ) })

}


//
//data class UserPrincipal(
//    val id: Long,
//    val email: String,
//    val name :String,
//    val phoneNumber: String,
//    val account: Long,
//    val authorities: Collection<GrantedAuthority>
//) {
//
//
//    constructor(id: Long, email: String, name:String, phoneNumber:String, account:Long, roles: Set<String>) : this(
//        id,
//        email,
//        name,
//        phoneNumber,
//        account,
//        roles.map { SimpleGrantedAuthority("ROLE_$it" ) }
//    )
//
//
//    constructor(id: Long, email: String, name:String, phoneNumber:String, account:Long) : this(
//        id,
//        email,
//        name,
//        phoneNumber,
//        account,
//        emptyList() // authorities 필드만 초기화
//    )
//

//    추가된 부분
//    constructor(id: Long, email: String, name:String, phoneNumber:String, account:Long, role:String) : this(
//        id,
//        email,
//        name,
//        phoneNumber,
//        account,
//        role,
//        emptyList()
//    )





