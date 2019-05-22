package com.wuriyanto.yoben.modules.user.domain

import java.time.LocalDateTime

data class User(var id: String,
                var firstName: String,
                var lastName: String,
                var email: String,
                var password: String
) {
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null
}