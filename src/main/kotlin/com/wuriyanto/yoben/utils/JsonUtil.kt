package com.wuriyanto.yoben.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.StringWriter

val mapper = jacksonObjectMapper()

fun dataToJson(data: Any?): String {
    var writer = StringWriter()
    mapper.writeValue(writer, data)
    return writer.toString()
}

// https://kotlinlang.org/docs/reference/reflection.html
fun <T> jsonToData(clazz: Class<T>, data: ByteArray): T {
    return mapper.readValue<T>(data, clazz)
}