package com.example.kotlinTemplate.dto

import com.example.kotlinTemplate.utils.TimeZoneDateSerializer
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.time.LocalDateTime
import java.time.ZonedDateTime

class Member {

    data class MemberResponse(
            var id: Int? = null,
            val email: String? = null,
            val type: String? = null,
            var status: Int? = null,
            var phone: String? = null,
            var deviceToken: String? = null,
            val rewardCode: String? = null,
            @JsonSerialize(using = TimeZoneDateSerializer::class)
            val createdTime: LocalDateTime? = null,

            @JsonSerialize(using = TimeZoneDateSerializer::class)
            var updatedTime: LocalDateTime? = null,

            @JsonSerialize(using = TimeZoneDateSerializer::class)
            var deletedTime: LocalDateTime? = null,
    )
}