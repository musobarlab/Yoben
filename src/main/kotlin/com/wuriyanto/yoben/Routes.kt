package com.wuriyanto.yoben

import org.eclipse.jetty.http.HttpStatus
import spark.Request
import spark.Response
import spark.Route

class IndexRoute: Route {

    override fun handle(req: Request?, res: Response?): Any {
        return "i am up"
    }

}

class NotFoundRoute: Route {

    override fun handle(req: Request?, res: Response?): Any {
        res?.status(HttpStatus.NOT_FOUND_404)
        return "page not found"
    }

}