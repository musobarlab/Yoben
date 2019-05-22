package com.wuriyanto.yoben.utils

import com.wuriyanto.yoben.modules.user.domain.User
import org.junit.Test
import kotlin.test.assertEquals

class JsonUtilTest {

    @Test fun testDataToJson() {
        var user = User("1", "Alex", "Kok", "alex@yahoo.com", "12345")
        val result = dataToJson(user)
        val expected = """{"id":"1","firstName":"Alex","lastName":"Kok","email":"alex@yahoo.com","password":"12345"}"""
        when(result) {
            is Ok -> assertEquals(expected, result.value)
            is Error -> println(result.value)
        }
    }

    @Test fun testJsonToData() {
        var expected = User("1", "Alex", "Kok", "alex@yahoo.com", "12345")
        val data = """{"id":"1","firstName":"Alex","lastName":"Kok","email":"alex@yahoo.com","password":"12345"}"""
        val user = jsonToData(User::class.java, data.toByteArray())
        assertEquals(expected, user)
    }
}