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

package com.wuriyanto.yoben.jwt

import com.wuriyanto.yoben.utils.Error
import com.wuriyanto.yoben.utils.Ok
import io.jsonwebtoken.impl.crypto.RsaProvider
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull


class JwtServiceTest {

    private val k = RsaProvider.generateKeyPair()

    @Test fun testGenerateAndValidate() {
        val privateKey = k.private
        val publicKey = k.public

        val jwtService = JwtService(privateKey, publicKey)

        var calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 17)
        calendar.set(Calendar.MINUTE, 30)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.MONTH, Calendar.AUGUST)
        calendar.set(Calendar.YEAR, 2019)
        calendar.set(Calendar.DATE, 13)

        val jwtTokenResult = jwtService.generate(
                CustomClaim("001", "wuriyanto.com", "my-service", calendar.time)
        )

        when(jwtTokenResult){
            is Ok -> {
                val jwtToken = "Bearer ${jwtTokenResult.value}"
                println(jwtToken)

                val validateResult = jwtService.validate(jwtToken)

                when(validateResult) {
                    is Ok -> {
                        assertNotNull(validateResult.value)

                        val expectedSubject = "001"
                        val v = validateResult.value

                        assertEquals(expectedSubject, v?.subject)

                    }

                    is Error -> assertNull(validateResult.value)
                }

            }
            is Error -> assertNull(jwtTokenResult.value)
        }
    }

    @Test fun testGenerateAndValidateErrorEmpty() {
        val privateKey = k.private
        val publicKey = k.public

        val jwtService = JwtService(privateKey, publicKey)

        var calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 17)
        calendar.set(Calendar.MINUTE, 30)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.MONTH, Calendar.AUGUST)
        calendar.set(Calendar.YEAR, 2019)
        calendar.set(Calendar.DATE, 13)

        val jwtTokenResult = jwtService.generate(null)

        when(jwtTokenResult){
            is Ok -> {
                assertNull(jwtTokenResult.value)

                val jwtToken = "Bearer ${jwtTokenResult.value}"

                val validateResult = jwtService.validate(jwtToken)

                when(validateResult) {
                    is Ok -> {
                        assertNotNull(validateResult.value)

                        val expectedSubject = "001"
                        val v = validateResult.value

                        assertEquals(expectedSubject, v?.subject)

                    }

                    is Error -> assertNotNull(validateResult.value)
                }

            }
            is Error -> assertNotNull(jwtTokenResult.value)
        }
    }
}