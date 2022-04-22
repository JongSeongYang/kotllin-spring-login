package com.example.kotlinTemplate.repository

import com.example.kotlinTemplate.domain.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository: JpaRepository<MemberEntity, Int> {

    fun findByEmailAndDeletedTimeIsNull(email:String): MemberEntity?
}