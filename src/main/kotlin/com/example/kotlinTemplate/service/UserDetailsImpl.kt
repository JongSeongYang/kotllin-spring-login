//package com.example.KotlinTemplate.service
//
//import com.example.KotlinTemplate.domain.MemberEntity
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.AuthorityUtils
//import org.springframework.security.core.userdetails.UserDetails
//
//class UserDetailsImpl(val member:MemberEntity): UserDetails {
//    var enabled: Boolean = true
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.createAuthorityList()
//
//    override fun getPassword(): String = member.password
//
//    override fun getUsername(): String = member.password
//
//    override fun isAccountNonExpired(): Boolean = enabled
//
//    override fun isAccountNonLocked(): Boolean = enabled
//
//    override fun isCredentialsNonExpired(): Boolean = enabled
//
//    override fun isEnabled(): Boolean = enabled
//}