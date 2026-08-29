package com.example.app_minuta.data

data class User(
    val name: String,
    val username: String,
    val pass: String,
    val diet: String
)
val registeredUsers = mutableListOf<User>()