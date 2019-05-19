/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wuriyanto.yoben

import io.github.cdimascio.dotenv.Dotenv
import org.eclipse.jetty.http.HttpStatus
import spark.Request
import spark.Response
import spark.Route
import spark.Spark
import sun.misc.Signal

/*

  @author wuriyanto
  Created on May 19, 2019
*/

fun main(args : Array<String>) {

    val env = Dotenv.load()

    var port = 9000

    if (env.get("HTTP_PORT") != null) {
        port = Integer.parseInt(env.get("HTTP_PORT"))
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
        Spark.notFound(NotFoundRoute())

        Spark.get("/", IndexRoute())
    }

    fun stop() {
        Spark.stop()
    }
}

class IndexRoute: Route {

    override fun handle(req: Request?, res: Response?): Any {
        return "i am up"
    }

}

class NotFoundRoute: Route {

    override fun handle(req: Request?, res: Response): Any {
        res.status(HttpStatus.NOT_FOUND_404)
        return "page not found"
    }

}
