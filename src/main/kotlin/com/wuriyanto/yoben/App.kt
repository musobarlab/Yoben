/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wuriyanto.yoben

import com.wuriyanto.yoben.modules.user.delivery.SparkUserHttpHandler
import com.wuriyanto.yoben.modules.user.domain.User
import com.wuriyanto.yoben.modules.user.repository.UserRepositoryInMemory
import com.wuriyanto.yoben.modules.user.usecase.UserUsecase
import io.github.cdimascio.dotenv.Dotenv
import spark.Spark
import sun.misc.Signal
import java.time.LocalDateTime

/*

  @author wuriyanto
  Created on May 19, 2019
*/

fun main(args : Array<String>) {

    val env = Dotenv.load()

    var port = 9000

    if (env["HTTP_PORT"] != null) {
        port = Integer.parseInt(env["HTTP_PORT"])
    }

    val server = Server(port)

    shutDown(server)

    server.start()
}

fun shutDown(server: Server) {
    Signal.handle(Signal("INT")) {
        println("app interrupted")
        server.stop()
    }

    Signal.handle(Signal("TERM")) {
        println("app terminated")
        server.stop()
    }
}

class Server(private val port: Int) {

    fun start() {
        Spark.port(port)
        Spark.notFound(notFoundRoute)

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
        val userSparkHttpHandler = SparkUserHttpHandler(userUsecase)

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

