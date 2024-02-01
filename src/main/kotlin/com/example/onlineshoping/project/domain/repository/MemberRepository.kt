package com.example.onlineshoping.project.domain.repository

import com.example.onlineshoping.project.domain.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository:JpaRepository<Member,Long> {
    fun findByEmail(email: String): Member?
}