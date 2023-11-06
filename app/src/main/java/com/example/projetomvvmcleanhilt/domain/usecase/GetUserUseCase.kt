package com.example.projetomvvmcleanhilt.domain.usecase

import com.example.projetomvvmcleanhilt.domain.models.User
import com.example.projetomvvmcleanhilt.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun getUsers() : List<User>{
        try {
            //Regras de negocio

            //recuperar Usuarios
            val listUsers = userRepository.getUsers()
            //a partir disto, pode ocorrer verificações das mais infitas maneiras




        }catch (error : Exception){
            error.printStackTrace()

            return emptyList()
        }


    }
}