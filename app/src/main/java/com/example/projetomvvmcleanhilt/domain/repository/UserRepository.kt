package com.example.projetomvvmcleanhilt.domain.repository

import com.example.projetomvvmcleanhilt.domain.models.User

interface UserRepository {
    suspend fun getUsers() : List<User>
    suspend fun salvarUsuario (usuario : User) : Boolean
    suspend fun listarFavoritos () : List<User>
    suspend fun removerUsuario (idUsuario : Int) : Boolean
}