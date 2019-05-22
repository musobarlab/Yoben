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

package com.wuriyanto.yoben.modules.repository

import com.wuriyanto.yoben.utils.ErrorMessage
import com.wuriyanto.yoben.utils.Ok
import com.wuriyanto.yoben.utils.Result

interface IBaseRepository<T, K> {

    fun save(t: T): Result<T?, ErrorMessage>

    fun findById(k: K): Result<T?, ErrorMessage>

    fun delete(k: K): Result<T?, ErrorMessage>

    fun findAll(): Result<List<T>, ErrorMessage>
}

open class BaseRepositoryInMemory<T, K>(private val db: MutableMap<K, T>): IBaseRepository<T, K> {

    override fun save(t: T): Result<T?, ErrorMessage> = Ok(t)

    override fun findById(k: K): Result<T?, ErrorMessage> = Ok(db[k])

    override fun delete(k: K): Result<T?, ErrorMessage> = Ok(db.remove(k))

    override fun findAll(): Result<List<T>, ErrorMessage> = Ok(ArrayList(db.values))


}