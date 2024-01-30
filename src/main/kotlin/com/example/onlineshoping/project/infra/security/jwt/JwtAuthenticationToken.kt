package com.example.onlineshoping.project.infra.security.jwt


import com.example.onlineshoping.project.infra.security.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.WebAuthenticationDetails
import java.io.Serializable

class JwtAuthenticationToken(
    private val principal: UserPrincipal,
    // 요청한 Address 정보, sessionId등을 담음 (로깅 용도)
    details: WebAuthenticationDetails,
) : AbstractAuthenticationToken(principal.authorities), Serializable {

    init {
        // JWT 검증이 됐을시에 바로 생성할 예정이므로, 생성시 authenticated를 true로 설정
        super.setAuthenticated(true)
        super.setDetails(details)
    }

    override fun getPrincipal() = principal

    override fun getCredentials() = null

    override fun isAuthenticated(): Boolean {
        return true
    }

}

//9. JwtAuthenticationToken: JwtAuthenticationToken 객체가 생성됨.
//10. JwtAuthenticationToken: JwtAuthenticationToken의 principal 및 details를 설정함.
//11. JwtAuthenticationToken: isAuthenticated() 메서드가 호출되어 true를 반환함.
//12. OncePerRequestFilter: 다음 필터(인증 필터 체인)로 요청 및 응답을 전달함.