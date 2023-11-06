package com.example.projetomvvmcleanhilt.data.dto

data class ResponseAPI(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<UserDTO>
)