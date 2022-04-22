package com.example.kotlinTemplate.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "Member")
class MemberEntity(email:String,
                   type:String,
                   password:String,
                   transferPassword:String,
                   phone:String,
                   rewardCode:String,
                   deviceToken:String) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(nullable = false)
    val email: String = email

    @Column(nullable = false)
    val type: String = type

    var status: Int? = null
        protected set

    @Column(nullable = false)
    var password: String = password
        protected set

    @Column(nullable = false)
    var transferPassword: String = transferPassword
        protected set

    @Column(nullable = false)
    var phone: String = phone
        protected set

    @Column(nullable = false)
    var deviceToken: String = deviceToken
        protected set

    @Column(nullable = false)
    val rewardCode: String = rewardCode

    @Column(name="passwordFailCnt")
    var signInFailCount: Int = 0
        protected set

    var deletedTime: LocalDateTime? = null
        protected set

    // set
    fun changePwd(password: String) {
        this.password = password
    }

    fun changePhone(phone: String) {
        this.phone = phone
    }

    fun changeDeviceToken(deviceToken: String) {
        this.deviceToken = deviceToken
    }

    fun changeDeletedTime(deletedTime: LocalDateTime) {
        this.deletedTime = deletedTime
    }

    fun changeStatus(status: Int) {
        this.status = status
    }

    fun changeFailCnt(failCnt: Int) {
        this.signInFailCount = failCnt
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return super.toString()
    }
}