package com.example.projetomvvmcleanhilt.domain.models

import com.example.projetomvvmcleanhilt.data.dto.Address

data class User (
    val id : Int,
    val firstName: String,
    val lastName: String,
    val address: String,
    val age: Int,
    val email: String,
    val phone: String,
    val image: String,


)