package com.wuriyanto.yoben.modules.repository

interface IBaseRepository<T, K> {

    fun save(t: T): T?

    fun findById(k: K): T?

    fun delete(k: K): T?

    fun findAll(): List<T>
}

open class BaseRepositoryInMemory<T, K>(private val db: MutableMap<K, T>): IBaseRepository<T, K> {

    override fun save(t: T): T? {
        return t
    }

    override fun findById(k: K): T? {
        return db[k]
    }

    override fun delete(k: K): T? {
        return db.remove(k)
    }

    override fun findAll(): List<T> {
        return ArrayList(db.values)
    }

}