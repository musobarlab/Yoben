package com.wuriyanto.yoben.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.IOException
import java.io.StringWriter

val mapper = jacksonObjectMapper()

fun dataToJson(data: Any?): Result<String, ErrorMessage> {
    var writer = StringWriter()
    try {
        mapper.writeValue(writer, data)
    } catch (e: IOException) {
        return Error(ErrorMessage("error parsing object"))
    }
    return Ok(writer.toString())
}

// https://kotlinlang.org/docs/reference/reflection.html
fun <T> jsonToData(clazz: Class<T>, data: ByteArray): T {
    return mapper.readValue<T>(data, clazz)
}