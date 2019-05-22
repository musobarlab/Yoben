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

import io.github.cdimascio.dotenv.Dotenv
import sun.misc.Signal

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


