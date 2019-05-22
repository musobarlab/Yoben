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