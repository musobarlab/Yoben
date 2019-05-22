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

package com.wuriyanto.yoben.modules.user.repository

import com.wuriyanto.yoben.modules.repository.BaseRepositoryInMemory
import com.wuriyanto.yoben.modules.repository.IBaseRepository
import com.wuriyanto.yoben.modules.user.domain.User
import com.wuriyanto.yoben.utils.ErrorMessage
import com.wuriyanto.yoben.utils.Ok
import com.wuriyanto.yoben.utils.Result


interface IUserRepository: IBaseRepository<User, String> {

    fun findByEmail(email: String): Result<User?, ErrorMessage>

}

class UserRepositoryInMemory(private var db: MutableMap<String, User>): BaseRepositoryInMemory<User, String>(db), IUserRepository {

    override fun save(t: User): Result<User?, ErrorMessage> = Ok(db.put(t.id, t))

    override fun findByEmail(email: String): Result<User?, ErrorMessage> =
            Ok(db.values.stream().filter { t -> t.email == email }.findFirst().orElse(null))

}