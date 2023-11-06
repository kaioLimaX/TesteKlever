package com.example.projetomvvmcleanhilt.domain.usecase

import com.example.projetomvvmcleanhilt.domain.models.User
import com.example.projetomvvmcleanhilt.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): List<User> {
        return try {
            //Regras de negocio

            //recuperar Usuarios
            userRepository.getUsers()
            //a partir disto, pode ocorrer verificações das mais infitas maneiras
            //neste caso, apenas retornaremos uma lista de usuarios


        } catch (error: Exception) {
            error.printStackTrace()

            return emptyList()
        }


    }
}