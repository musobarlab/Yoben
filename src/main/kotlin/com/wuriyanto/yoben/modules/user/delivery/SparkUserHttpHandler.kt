package com.wuriyanto.yoben.modules.user.delivery

import com.wuriyanto.yoben.modules.user.usecase.IUserUsecase
import com.wuriyanto.yoben.utils.CustomResponse
import com.wuriyanto.yoben.utils.Error
import com.wuriyanto.yoben.utils.Ok
import com.wuriyanto.yoben.utils.dataToJson
import org.eclipse.jetty.http.HttpStatus
import spark.Route
import spark.RouteGroup
import spark.Spark

class SparkUserHttpHandler(val userUsecase: IUserUsecase): RouteGroup {

    fun index(): Route {
        return Route{req, res ->
            val result = userUsecase.getUsers()
            when(result) {
                is Ok -> dataToJson(CustomResponse(HttpStatus.OK_200, true, result.value, "success get users"))
                is Error -> CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR_500, false, null, "fail get users")
            }
        }
    }

    override fun addRoutes() {
        Spark.get("", index())
    }

}