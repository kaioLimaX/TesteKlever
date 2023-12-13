package com.example.projetomvvmcleanhilt.domain.repository

import android.util.Log
import com.example.projetomvvmcleanhilt.data.dto.toUser
import com.example.projetomvvmcleanhilt.data.local.IUserDAO
import com.example.projetomvvmcleanhilt.data.remote.DummyAPI
import com.example.projetomvvmcleanhilt.domain.models.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dummyAPI: DummyAPI,
    private val iUserDAO: IUserDAO
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

    override suspend fun salvarUsuario(usuario: User): Boolean {
        return try {
            val result = iUserDAO.adicionarUsuario(usuario)
            when(result){
                true -> true
                false -> false
            }

        }catch (e : Exception){
            false
        }
    }

    override suspend fun listarFavoritos(): List<User> {
        return try {
            val list = iUserDAO.listarUsuarios()

            return list

        }catch (e : Exception){
            emptyList()
        }
    }

    override suspend fun removerUsuario(idUsuario: Int): Boolean {
        return  try {
            val result = iUserDAO.removerUsuario(idUsuario)

            when(result){
                true -> true
                false -> false
            }

        }catch (e : Exception){
            Log.i("info_db", "Sucesso ao Remover Item Tabela - ${e.message} ") // log do sistema
            false
        }
    }
}