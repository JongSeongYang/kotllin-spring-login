package com.example.kotlinTemplate.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class TimeZoneDateSerializer : JsonSerializer<Any?>() {
    @Throws(IOException::class)
    override fun serialize(value: Any?, gen: JsonGenerator, serializers: SerializerProvider) {
        val fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val odt = OffsetDateTime.of(value as LocalDateTime?, ZoneOffset.UTC)
        gen.writeString(fmt.format(odt))
    }
}
