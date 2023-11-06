package com.example.projetomvvmcleanhilt.data.repository

import android.util.Log
import com.example.projetomvvmcleanhilt.data.dto.toUser
import com.example.projetomvvmcleanhilt.data.remote.DummyAPI
import com.example.projetomvvmcleanhilt.domain.models.User
import com.example.projetomvvmcleanhilt.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dummyAPI: DummyAPI
) : UserRepository {

    override suspend fun getUsers(): List<User> {

        try {
            val response = dummyAPI.getUsers()
            if (response.isSuccessful && response.body() != null) {
                val responseAPI = response.body()

                val listUser = responseAPI?.users

                if (listUser != null) {
                    /*val users = listUser.map { userDTO ->
                        userDTO.toUser()

                    }*/
                    return listUser.map { it.toUser() }
                }
            }else{
                Log.i("list_user", "getUsers:  ${response.message()}")
            }


        } catch (error: Exception) {
            error.printStackTrace()
        }

        return emptyList()
    }
}