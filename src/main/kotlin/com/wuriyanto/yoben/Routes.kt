package com.wuriyanto.yoben

import org.eclipse.jetty.http.HttpStatus
import spark.Route

val indexRoute = Route { req, res -> "i am up" }

val notFoundRoute = Route { req, res ->
    res?.status(HttpStatus.NOT_FOUND_404)
    "page not found"
}