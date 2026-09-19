package com.example.app_minuta.data

data class User(
    val name: String,
    val username: String,
    val pass: String,
    val diet: String,
    val activityLevel: String
)

class UserRepository {
    private val registeredUsers = mutableListOf<User>()

    fun getUser(username: String): User? {
        return registeredUsers.filterNotNull().find { it.username == username }
    }

    fun addUser(user: User): Boolean {
        if (registeredUsers.size < 5 && !userExists(user.username)) {
            registeredUsers.add(user)
            return true
        }
        return false
    }

    fun validateCredentials(username: String, pass: String): Boolean {
        return registeredUsers.any { it.username == username && it.pass == pass }
    }

    fun userExists(username: String): Boolean {
        return registeredUsers.any { it.username == username }
    }

    fun findUser(username: String?): User? {
        return registeredUsers.find { it.username == username }
    }

    fun isFull(): Boolean {
        return registeredUsers.size >= 5
    }
}

val userRepository = UserRepository()