//package com.example.KotlinTemplate.service
//
//import com.example.KotlinTemplate.domain.MemberEntity
//import com.example.KotlinTemplate.repository.MemberRepository
//import com.hiandd.wiki.enum.ExceptionCode
//import com.hiandd.wiki.errors.CustomResponseStatusException
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.stereotype.Service
//
//@Service
//class UserDetailsService(private val memberRepository: MemberRepository) {
//    fun loadUserByEmail(email:String) : UserDetails{
//        val memberEntity:MemberEntity = memberRepository.findByEmail(email)
//                ?: throw CustomResponseStatusException(ExceptionCode.ID_NOT_FOUND,"")
//        return UserDetailsImpl(memberEntity)
//    }
//}