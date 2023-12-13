package com.example.projetomvvmcleanhilt.domain.usecase

import android.util.Log
import com.example.projetomvvmcleanhilt.domain.models.User
import com.example.projetomvvmcleanhilt.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository,

    ) {

    suspend fun recuperarListaAPI(): List<User> {
        return try {
            //Regras de negocio

            //recuperar Usuarios
            val list = userRepository.getUsers()
            return list

            //a partir disto, pode ocorrer verificações das mais infitas maneiras
            //neste caso, apenas retornaremos uma lista de usuarios


        } catch (error: Exception) {
            error.printStackTrace()

            return emptyList()
        }


    }

     suspend fun adicionarUsuario(usuario : User) : Boolean{

        return try {
           val result =  userRepository.salvarUsuario(usuario)
            when(result){
                true -> true
                false -> false
            }
        }catch (e : Exception){
            e.printStackTrace()
           false
        }
    }

    suspend fun listarFavoritos() : List<User>{

        return try {
             val list = userRepository.listarFavoritos()
            Log.i("info_favoritos", "recuperarListaUseCase: $list ")
            return list

        }catch (e : Exception){
            emptyList()
        }
    }

    suspend fun removerUsuario(id : Int) : Boolean {
        return try{
            val result = userRepository.removerUsuario(id)
            when(result){
                true -> true
                false -> false
            }

        }catch (e : Exception){
            e.printStackTrace()
            false

        }
    }
}