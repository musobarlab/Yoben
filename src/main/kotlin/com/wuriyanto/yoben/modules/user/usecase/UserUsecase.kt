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

package com.wuriyanto.yoben.modules.user.usecase

import com.wuriyanto.yoben.modules.user.domain.User
import com.wuriyanto.yoben.modules.user.repository.IUserRepository
import com.wuriyanto.yoben.utils.Error
import com.wuriyanto.yoben.utils.ErrorMessage
import com.wuriyanto.yoben.utils.Ok
import com.wuriyanto.yoben.utils.Result
import java.time.LocalDateTime

interface IUserUsecase {

    fun createUser(user: User): Result<User, ErrorMessage>

    fun getUsers(): Result<List<User>, ErrorMessage>
}

class UserUsecase(private val userRepository: IUserRepository): IUserUsecase {

    override fun createUser(user: User): Result<User, ErrorMessage> {
        if(user.id == "" || user.email == "") {
            return Error(ErrorMessage("field cannot be empty"))
        }

        user.createdAt = LocalDateTime.now()
        user.updatedAt = LocalDateTime.now()
        val result =  userRepository.save(user)
        return when(result) {
            is Ok -> Ok(user)
            is Error -> Error(ErrorMessage(result.value.message))
        }
    }

    override fun getUsers(): Result<List<User>, ErrorMessage> = userRepository.findAll()

}