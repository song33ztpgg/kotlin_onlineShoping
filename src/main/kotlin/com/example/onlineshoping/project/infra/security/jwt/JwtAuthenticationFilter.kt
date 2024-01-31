//import com.example.onlineshoping.project.infra.security.jwt.JwtAuthenticationToken

//package com.example.onlineshoping.project.infra.security.jwt
//
//import com.example.onlineshoping.project.infra.security.SellerPrincipal
//import com.example.onlineshoping.project.infra.security.UserPrincipal
//import jakarta.servlet.FilterChain
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.http.HttpHeaders
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
//import org.springframework.stereotype.Component
//import org.springframework.web.filter.OncePerRequestFilter
//
//@Component
//class JwtAuthenticationFilter(
//    private val jwtPlugin: JwtPlugin
//) : OncePerRequestFilter() {
//
//    companion object {
//        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
//    }
//
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        val jwt = request.getBearerToken()  // HTTP 요청 헤더에서 JWT 토큰 추출
//
//        if (jwt != null) {
//            jwtPlugin.validateToken(jwt)
//                .onSuccess {  //jwt 인증이 되었을 경우
//                    val userId = it.payload.subject.toLong()  // 사용자 ID 추출
//                    val email = it.payload.get("email", String::class.java)
//                    val name = it.payload.get("name", String::class.java)
//                    val phoneNumber = it.payload.get("phoneNumber", String::class.java)
//                    val balance = it.payload.get("balance", Integer::class.java).toLong()
//
//                    val principal = UserPrincipal(
//                        id = userId,
//                        email = email,
//                        name = name,
//                        phoneNumber = phoneNumber,
//                        balance = balance
////                        roles = setOf(role)
//                    )
//
//                    // Authentication 구현체 생성
//                    val authentication = JwtAuthenticationToken(
//                        principal = principal,
//                        // request로 부터 요청 상세정보 생성,추가
//                        details = WebAuthenticationDetailsSource().buildDetails(request)
//                    )
//                    // SecurityContext에 authentication 객체 저장  ,  보안 컨텍스트에 인증 정보 저장
//                    SecurityContextHolder.getContext().authentication = authentication
//                }
//        }
//
//        //다음 필더에게 전달
//        filterChain.doFilter(request, response)
//
//    }
//
//
//    // HTTP 요청 헤더에서 Bearer 토큰 추출
//    private fun HttpServletRequest.getBearerToken(): String? {
//        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
//        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
//    }
//}

//http요청에서 bear토큰 추출
//추찰한 토큰 유효성 검사
//userprincipal생성
//jwtAuthenticatorToken을 통해 authentication 생성
//SecurityContextHolder 에 Authentication 저장
//다음 필터로 요청전달
///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
// HTTP 요청 헤더에서 Bearer 토큰 추출

package com.example.onlineshoping.project.infra.security.jwt
import com.example.onlineshoping.project.infra.security.jwt.JwtAuthenticationToken
import com.example.onlineshoping.project.infra.security.SellerPrincipal
import com.example.onlineshoping.project.infra.security.UserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin
) : OncePerRequestFilter() {

    companion object {
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()  // HTTP 요청 헤더에서 JWT 토큰 추출

        if (jwt != null) {
            jwtPlugin.validateToken(jwt)
                .onSuccess {  //jwt 인증이 되었을 경우
                    val userId = it.payload.subject.toLong()  // 사용자 ID 추출
                    val email = it.payload.get("email", String::class.java)
                    val name = it.payload.get("name", String::class.java)

                    val phoneNumber = it.payload.get("phoneNumber", String::class.java)
                    val balance = it.payload.get("balance", Integer::class.java).toLong()
                    val account = it.payload.get("account", Integer::class.java).toLong()?:0

                    val principal = UserPrincipal(
                        id = userId,
                        email = email,
                        name = name,
                        phoneNumber = phoneNumber,
                        balance = balance
                    )


                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    )

                    SecurityContextHolder.getContext().authentication = authentication
                }
        }

        filterChain.doFilter(request, response)

    }


    // HTTP 요청 헤더에서 Bearer 토큰 추출
    private fun HttpServletRequest.getBearerToken(): String? {
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
    }
}



//if( it.payload.get("account", Integer::class.java).toLong() == null){
//    val principal = UserPrincipal(
//        id = userId,
//        email = email,
//        name = name,
//        phoneNumber = phoneNumber,
//        balance = balance
//    )
//} else {
//    val seller = SellerPrincipal(
//        id =  userId,
//        email = email,
//        name = name,
//        account =  account
//    )
//
//}






//                    if (it.payload.get("phoneNumber", String::class.java) == null
//                        || it.payload.get("balance", Integer::class.java).toLong() == null
//                    ) {
//
//
//                        account = it.payload.get("account", Integer::class.java).toLong() ?: 0
//                        val principal = UserPrincipal(
//                            id = userId,
//                            email = email,
//                            name = name,
//                            phoneNumber = "null",
//                            balance = 0,
////                            account = account
//                        )
//
//                        // Authentication 구현체 생성
//                        val authentication = JwtAuthenticationToken(
//                            principal = principal,
//                            // request로 부터 요청 상세정보 생성,추가
//                            details = WebAuthenticationDetailsSource().buildDetails(request)
//                        )
//                        // SecurityContext에 authentication 객체 저장  ,  보안 컨텍스트에 인증 정보 저장
//                        SecurityContextHolder.getContext().authentication = authentication
//
//                    } else {
//
//                        phoneNumber = it.payload.get("phoneNumber", String::class.java) ?: ""
//                        balance = it.payload.get("balance", Integer::class.java).toLong() ?: 0
//
//                        val principal = UserPrincipal(
//                            id = userId,
//                            email = email,
//                            name = name,
//                            phoneNumber = phoneNumber,
//                            balance = balance
//                        )
//                        // Authentication 구현체 생성
//                        val authentication = JwtAuthenticationToken(
//                            principal = principal,
//                            // request로 부터 요청 상세정보 생성,추가
//                            details = WebAuthenticationDetailsSource().buildDetails(request)
//                        )
//                        // SecurityContext에 authentication 객체 저장  ,  보안 컨텍스트에 인증 정보 저장
//                        SecurityContextHolder.getContext().authentication = authentication
//
//                    }