package com.example.kotlinTemplate.service

import com.example.kotlinTemplate.domain.MemberEntity
import com.example.kotlinTemplate.dto.Auth
import com.example.kotlinTemplate.enum.ExceptionCode
import com.example.kotlinTemplate.exception.CustomResponseStatusException
import com.example.kotlinTemplate.repository.MemberRepository
import com.example.kotlinTemplate.utils.HashUtils
import com.example.kotlinTemplate.utils.JwtTokenProvider
import com.example.kotlinTemplate.utils.Log
import io.jsonwebtoken.ExpiredJwtException

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.servlet.http.HttpServletRequest

@Service
class AuthService(
        private val memberRepository: MemberRepository,
        private val jwtTokenProvider: JwtTokenProvider,
        private val hashUtils: HashUtils
) {

    companion object: Log {}

    @Transactional(noRollbackFor = [CustomResponseStatusException::class])
    fun signIn(request: HttpServletRequest,signInRequest: Auth.SignInRequest): Auth.SignInResponse {
        var token: String? = request.getHeader("Authorization")
        var validation: Boolean = false;
        var memberEntity: MemberEntity? = null
        // token 추출 및 유효성 검사
        if(null != token && token.startsWith("Bearer")){
            token = token.removePrefix("Bearer").trim()
            validation = checkValidateToken(token)
        }
        // token이 유효하지 않을 경우
        if(!validation){
            memberEntity = memberRepository.findByEmailAndDeletedTimeIsNull(signInRequest.email!!)
                    ?: throw (CustomResponseStatusException(ExceptionCode.SIGN_IN_FAIL,""))
            val equals: Boolean = hashUtils.toPasswordHash(signInRequest.password!!).equals(memberEntity.password)
            // 비밀번호가 틀릴 경우
            if(!equals){
                memberEntity.changeFailCnt(memberEntity.signInFailCount+1)
                checkPwdFailCnt(memberEntity)
                throw CustomResponseStatusException(ExceptionCode.SIGN_IN_FAIL,"")
            }
            // 비밀번호가 같을 경우
            else{
                memberEntity.changeFailCnt(0)
            }
        }
        // token이 유효할 경우
        else {
            memberEntity = memberRepository.findByEmailAndDeletedTimeIsNull(jwtTokenProvider.parseEmail(token!!))
                    ?: throw (CustomResponseStatusException(ExceptionCode.SIGN_IN_FAIL,""))
        }
        // deviceToken 갱신
        if(signInRequest.deviceToken != null)
            memberEntity.changeDeviceToken(signInRequest.deviceToken)
        return Auth.SignInResponse(true,jwtTokenProvider.createRegisterToken(memberEntity.id, memberEntity.type,memberEntity.email),"LOGIN_SUCCESS")
    }

    private fun checkPwdFailCnt(memberEntity: MemberEntity) : Int{
        val signInFailCount = memberEntity.signInFailCount
        if(signInFailCount >= 5)
            throw CustomResponseStatusException(ExceptionCode.WRONG_PWD_FIVE,"")
        return signInFailCount
    }

    private fun checkValidateToken(token:String): Boolean {
        return try {
            val claims = jwtTokenProvider.getAllClaims(token)
            jwtTokenProvider.validation(claims)
        } catch(e: ExpiredJwtException){
            logger().error("Token($token) Expired")
            false
        } catch (e: Exception){
            logger().error("Token($token) Wrong")
            false
        }
    }
}