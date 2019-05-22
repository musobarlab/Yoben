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

    init {
        db = hashMapOf(
                "1" to User("1", "Alex", "Kok", "alex@yahoo.com", "12345"),
                "2" to User("2", "Bob", "lee", "bob@yahoo.com", "12345")
        )
    }

    override fun save(t: User): Result<User?, ErrorMessage> {
        return Ok(db.put(t.id, t))
    }

    override fun findByEmail(email: String): Result<User?, ErrorMessage> =
            Ok(db.values.stream().filter { t -> t.email == email }.findFirst().orElse(null))

}