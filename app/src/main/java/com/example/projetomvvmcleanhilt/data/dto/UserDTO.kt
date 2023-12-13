package com.example.projetomvvmcleanhilt.data.dto

import com.example.projetomvvmcleanhilt.domain.models.User

data class UserDTO(
    val address: Address,
    val age: Int,
    val bank: Bank,
    val birthDate: String,
    val bloodGroup: String,
    val company: Company,
    val domain: String,
    val ein: String,
    val email: String,
    val eyeColor: String,
    val firstName: String,
    val gender: String,
    val hair: Hair,
    val height: Int,
    val id: Int,
    val image: String,
    val ip: String,
    val lastName: String,
    val macAddress: String,
    val maidenName: String,
    val password: String,
    val phone: String,
    val ssn: String,
    val university: String,
    val userAgent: String,
    val username: String,
    val weight: Double
)

fun UserDTO.toUser() : User {
    return User(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        address = this.address.address,
        age = this.age,
        email = this.email,
        phone = this.phone,
        image = this.image

    )
}
