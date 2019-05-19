package com.wuriyanto.yoben.modules.user.repository

import com.wuriyanto.yoben.modules.repository.BaseRepositoryInMemory
import com.wuriyanto.yoben.modules.repository.IBaseRepository
import com.wuriyanto.yoben.modules.user.domain.User


interface IUserRepository: IBaseRepository<User, String> {

    fun findByEmail(email: String): User?

}

class UserRepository(var db: MutableMap<String, User>): BaseRepositoryInMemory<User, String>(db), IUserRepository {

    init {
        db = hashMapOf(
                "1" to User("1", "Alex", "Kok", "alex@yahoo.com", "12345"),
                "2" to User("2", "Bob", "lee", "bob@yahoo.com", "12345")
        )
    }

    override fun save(t: User): User? {
        return db.put(t.id, t)
    }

    override fun findByEmail(email: String): User? {
        return db.values.find { it.email == email }
    }

}