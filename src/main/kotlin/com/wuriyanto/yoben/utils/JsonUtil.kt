/*
 * Copyright 2019 wuriyanto.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wuriyanto.yoben.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.StringWriter

val mapper = jacksonObjectMapper()

fun dataToJson(data: Any?): String{
    var writer = StringWriter()
    try {
        mapper.writeValue(writer, data)
    } catch (e: Exception) {
        throw e
    }
    return writer.toString()
}

// https://kotlinlang.org/docs/reference/reflection.html
fun <T> jsonToData(clazz: Class<T>, data: ByteArray): T = mapper.readValue<T>(data, clazz)