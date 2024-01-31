package com.example.onlineshoping.project.infra.security
import com.example.onlineshoping.project.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // 추가!!
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntrypoint: AuthenticationEntryPoint
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/login",
                    "/carts",
                    "/buyer/signup",
                    "/seller/signup",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/error"
                ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling{
                it.authenticationEntryPoint(authenticationEntrypoint)
            }
            .build()
    }

}

//7. SecurityConfig: JwtAuthenticationFilter가 UsernamePasswordAuthenticationFilter 전에 실행될 수 있도록 설정함.




//@Configuration
//@EnableWebSecurity
//class SecurityConfig(
//    private val jwtAuthenticationFilter: JwtAuthenticationFilter
//) {
//
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        return http
//            .httpBasic { it.disable() }
//            .formLogin { it.disable() }
//            .csrf { it.disable() }
//            .authorizeHttpRequests {
//                it.requestMatchers(
//                    "/login",
//                    "/signup",
//                    "/swagger-ui/**",
//                    "/v3/api-docs/**",
//                ).permitAll()
//                    // 위 URI를 제외하곤 모두 인증이 되어야 함.
//                    .anyRequest().authenticated()
//            }
//            // 기존 UsernamePasswordAuthenticationFilter 가 존재하던 자리에 JwtAuthenticationFilter 적용
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
//            .build()
//    }
//
//}


//@Configuration
//@EnableWebSecurity
//class SecurityConfig {
//
//    @Bean
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        return http
//            .httpBasic { it.disable() } // BasicAuthenticationFilter, DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter 제외
//            .formLogin { it.disable() } // UsernamePassworedAuthenticationFilter, DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter 제외
//            .csrf { it.disable() } // CsrfFilter 제외
//            .build()
//    }
//
//}