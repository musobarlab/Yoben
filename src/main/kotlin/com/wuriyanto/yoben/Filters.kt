package com.wuriyanto.yoben

import org.eclipse.jetty.http.HttpStatus
import spark.Filter
import spark.Spark

val jwtFilter = Filter{req, res ->
    val authorization = req.headers("Authorization")
    if (authorization == null) {
        Spark.halt(HttpStatus.UNAUTHORIZED_401, "auth not found")
    }

    println(authorization)
}

val jsonHeader = Filter{req, res -> res.header("Content-Type", "application/json")}