package com.example.onlineshoping.project.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

//    @Value("\${auth.jwt.issuer}") private val issuer:String,
//    @Value("\${auth.jwt.secret}") private val secret:String,
//    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour:Long

@Component
class JwtPlugin(
) {
    companion object {
        const val issuer = "team.sparta.com"
        const val secret = "PO4c8z41Hia5gJG3oeuFJMRYBB4Ws4aZ"
        const val accessTokenExpirationHour: Long = 168
    }

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

//    fun generateAccessToken(subject: String, name: String): String {
//        return generateToken(subject, name,Duration.ofHours(accessTokenExpirationHour))
//    }

    fun generateAccessToken(subject: String, email: String,name:String,phoneNumber:String,balance:Int): String {
        // subject, 만료기간과 role을 설정합니다.
        return generateToken(subject, email, name,phoneNumber,balance,Duration.ofHours(accessTokenExpirationHour))
    }



    private fun generateToken(subject: String,email: String,name:String,phoneNumber:String,balance:Int, expirationPeriod: Duration): String {
        val claims: Claims = Jwts.claims()
            .add(mapOf(
                "email" to email,
                "name" to name,
                "phoneNumber" to phoneNumber,
                "balance" to balance
                )).build()

        val now = Instant.now()
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }


}