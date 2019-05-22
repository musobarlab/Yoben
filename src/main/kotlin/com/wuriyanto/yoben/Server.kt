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

package com.wuriyanto.yoben

import com.wuriyanto.yoben.modules.user.delivery.SparkUserHttpHandler
import com.wuriyanto.yoben.modules.user.domain.User
import com.wuriyanto.yoben.modules.user.repository.UserRepositoryInMemory
import com.wuriyanto.yoben.modules.user.usecase.UserUsecase
import spark.Spark
import java.time.LocalDateTime

class Server(private val port: Int) {

    private val userSparkHttpHandler: SparkUserHttpHandler

    init {
        var alex = User("1", "Alex", "Kok", "alex@yahoo.com", "12345")
        alex.createdAt = LocalDateTime.now()
        alex.updatedAt = LocalDateTime.now()
        var bob = User("2", "Bob", "lee", "bob@yahoo.com", "12345")
        bob.createdAt = LocalDateTime.now()
        bob.updatedAt = LocalDateTime.now()

        var inMemoryDb =  hashMapOf(
                "1" to alex,
                "2" to bob
        )

        val userRepository = UserRepositoryInMemory(inMemoryDb)
        val userUsecase = UserUsecase(userRepository)
        userSparkHttpHandler = SparkUserHttpHandler(userUsecase)
    }

    fun start() {
        Spark.port(port)
        Spark.notFound(notFoundRoute)

        Spark.get("/", indexRoute)
        Spark.path("/api") {
            Spark.before("/*", jsonHeader)
            Spark.path("/users", userSparkHttpHandler)
        }
    }

    fun stop() {
        Spark.stop()
    }
}