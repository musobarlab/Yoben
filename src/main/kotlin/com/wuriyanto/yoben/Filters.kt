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