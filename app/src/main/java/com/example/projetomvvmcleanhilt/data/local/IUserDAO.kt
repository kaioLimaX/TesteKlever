package com.example.projetomvvmcleanhilt.data.local

import com.example.projetomvvmcleanhilt.domain.models.User

interface IUserDAO {
    fun listarUsuarios() : List<User>
    fun adicionarUsuario(usuario : User) : Boolean
    fun removerUsuario (id : Int) : Boolean

}