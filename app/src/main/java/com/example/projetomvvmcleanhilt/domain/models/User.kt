package com.example.projetomvvmcleanhilt.domain.models

import com.example.projetomvvmcleanhilt.data.dto.Address

data class User (
    val firstName: String,
    val lastName: String,
    val address: Address,
    val age: Int,
    val email: String,
    val phone: String,
    val image: String,


)