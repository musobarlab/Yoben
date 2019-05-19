package com.wuriyanto.yoben.modules.user.domain

data class User(var id: String,
                var firstName: String,
                var lastName: String,
                var email: String,
                var password: String)