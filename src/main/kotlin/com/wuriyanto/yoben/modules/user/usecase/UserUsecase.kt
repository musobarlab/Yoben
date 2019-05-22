package com.wuriyanto.yoben.modules.user.usecase

import com.wuriyanto.yoben.modules.user.domain.User
import com.wuriyanto.yoben.modules.user.repository.IUserRepository
import com.wuriyanto.yoben.utils.Error
import com.wuriyanto.yoben.utils.ErrorMessage
import com.wuriyanto.yoben.utils.Result

interface IUserUsecase {

    fun createUser(user: User): Result<User?, ErrorMessage>

    fun getUsers(): Result<List<User>, ErrorMessage>
}

class UserUsecase(private val userRepository: IUserRepository): IUserUsecase {

    override fun createUser(user: User): Result<User?, ErrorMessage> {
        if(user.id == "" || user.email == "") {
            return Error(ErrorMessage("field cannot be empty"))
        }
        return userRepository.save(user)
//        return when(result) {
//            is Ok -> Ok(result.value)
//            is Error -> Error(ErrorMessage(result.value.message))
//        }
    }

    override fun getUsers(): Result<List<User>, ErrorMessage> {
       return userRepository.findAll()
    }

}