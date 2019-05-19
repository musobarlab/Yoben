package com.wuriyanto.yoben.modules.user.domain

import org.junit.Test
import kotlin.test.assertEquals

class UserTest {

    @Test fun testCreateUser(): Unit {
        var user = User("1", "Alex", "Kok", "alex@yahoo.com", "12345")
        assertEquals(user, User("1", "Alex", "Kok", "alex@yahoo.com", "12345"))

        user.firstName = "Bob"
        assertEquals(user, User("1", "Bob", "Kok", "alex@yahoo.com", "12345"))

    }
}