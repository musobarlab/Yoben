package com.wuriyanto.yoben.modules.user.delivery

import com.wuriyanto.yoben.modules.user.domain.User
import com.wuriyanto.yoben.modules.user.usecase.IUserUsecase
import com.wuriyanto.yoben.utils.*
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
                is Error -> dataToJson(CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR_500, false, null, "fail get users"))
            }
        }
    }

    fun createUser(): Route {
        return Route{req, res ->
            if (req.bodyAsBytes().isEmpty()) {
                Spark.halt(HttpStatus.BAD_REQUEST_400, dataToJson(
                        CustomResponse(HttpStatus.BAD_REQUEST_400, false, null, "payload cannot be empty"))
                )
            }

            val body = req.bodyAsBytes()
            val userBody = jsonToData(User::class.java, body)
            val result = userUsecase.createUser(userBody)

            when(result) {
                is Ok -> {
                    val userDto = UserDto(
                            result.value.id,
                            result.value.firstName,
                            result.value.lastName,
                            result.value.email,
                            result.value.password,
                            result.value.createdAt.toString(),
                            result.value.updatedAt.toString())
                    dataToJson(CustomResponse(HttpStatus.CREATED_201, true, userDto, "success create user"))
                }
                is Error -> dataToJson(CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR_500, false, null, result.value.message))
            }
        }
    }

    override fun addRoutes() {
        Spark.get("", index())
        Spark.post("", createUser())
    }

}

data class UserDto(var id: String,
                   var firstName: String,
                   var lastName: String,
                   var email: String,
                   var password: String,
                   var createdAt: String,
                   var updatedAt: String)