package com.example.projetomvvmcleanhilt.domain.repository

import com.example.projetomvvmcleanhilt.domain.models.User

interface UserRepository {

    suspend fun getUsers() : List<User>
}