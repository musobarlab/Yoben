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

import com.wuriyanto.yoben.modules.user.domain.User
import org.junit.Test
import kotlin.test.assertEquals

class JsonUtilTest {

    @Test fun testDataToJson() {
        var user = User("1", "Alex", "Kok", "alex@yahoo.com", "12345")
        val result = dataToJson(user)
        val expected = """{"id":"1","firstName":"Alex","lastName":"Kok","email":"alex@yahoo.com","password":"12345","createdAt":null,"updatedAt":null}"""
        assertEquals(expected, result)
    }

    @Test fun testJsonToData() {
        var expected = User("1", "Alex", "Kok", "alex@yahoo.com", "12345")
        val data = """{"id":"1","firstName":"Alex","lastName":"Kok","email":"alex@yahoo.com","password":"12345"}"""
        val user = jsonToData(User::class.java, data.toByteArray())
        assertEquals(expected, user)
    }
}