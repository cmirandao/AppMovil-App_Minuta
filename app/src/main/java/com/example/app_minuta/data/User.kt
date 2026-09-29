package com.example.app_minuta.data

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

// Valores por defecto requeridos por Firebase Realtime Database para deserializar JSON
data class User(
    val name: String = "",
    val username: String = "",
    val pass: String = "",
    val diet: String = "Normal",
    val activityLevel: String = "Moderado"
)

class UserRepository {
    private val auth: FirebaseAuth = Firebase.auth
    private val database: DatabaseReference = Firebase.database.getReference("users")
    private val registeredUsers = mutableListOf<User>()

    init {
        // READ
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                registeredUsers.clear()
                for (child in snapshot.children) {
                    val user = child.getValue(User::class.java)
                    if (user != null) {
                        registeredUsers.add(user)
                    }
                }
                Log.d("FirebaseUsers", "Usuarios sincronizados: ${registeredUsers.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("FirebaseUsers", "Error al leer usuarios de Firebase", error.toException())
            }
        })
    }

    private fun formatAuthEmail(username: String): String {
        val clean = username.trim()
        return if (clean.contains("@")) clean else "$clean@appminuta.cl"
    }

    // Limpia caracteres no permitidos en las claves de Firebase Realtime Database (. # $ [ ])
    private fun sanitizeKey(username: String): String {
        return username.trim().replace(".", "_")
            .replace("#", "_")
            .replace("$", "_")
            .replace("[", "_")
            .replace("]", "_")
    }

    // CREATE
    fun registerUserWithFirebase(
        user: User,
        onResult: (Boolean, String) -> Unit
    ) {
        if (userExists(user.username)) {
            onResult(false, "El usuario ya se encuentra registrado.")
            return
        }

        val emailForAuth = formatAuthEmail(user.username)
        auth.createUserWithEmailAndPassword(emailForAuth, user.pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val key = sanitizeKey(user.username)
                    database.child(key).setValue(user)
                        .addOnSuccessListener {
                            if (!registeredUsers.any { it.username == user.username }) {
                                registeredUsers.add(user)
                            }
                            onResult(true, "Usuario registrado en Firebase exitosamente.")
                        }
                        .addOnFailureListener { e ->
                            onResult(false, "Error al guardar en base de datos: ${e.localizedMessage}")
                        }
                } else {
                    val key = sanitizeKey(user.username)
                    database.child(key).setValue(user)
                        .addOnSuccessListener {
                            if (!registeredUsers.any { it.username == user.username }) {
                                registeredUsers.add(user)
                            }
                            onResult(true, "Usuario guardado en Firebase Database.")
                        }
                        .addOnFailureListener { e ->
                            onResult(false, task.exception?.localizedMessage ?: e.localizedMessage ?: "Error de registro")
                        }
                }
            }
    }

    // LOGIN
    fun loginWithFirebase(
        username: String,
        pass: String,
        onResult: (Boolean, String) -> Unit
    ) {
        val emailForAuth = formatAuthEmail(username)
        auth.signInWithEmailAndPassword(emailForAuth, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Autenticación exitosa")
                } else {
                    if (validateCredentials(username, pass)) {
                        onResult(true, "Autenticación validada en Realtime Database")
                    } else {
                        onResult(false, "Credenciales incorrectas o usuario no encontrado")
                    }
                }
            }
    }

    // UPDATE
    fun updateUserDiet(username: String, newDiet: String, onResult: (Boolean) -> Unit = {}) {
        val key = sanitizeKey(username)
        database.child(key).child("diet").setValue(newDiet)
            .addOnSuccessListener {
                val index = registeredUsers.indexOfFirst { it.username == username }
                if (index != -1) {
                    registeredUsers[index] = registeredUsers[index].copy(diet = newDiet)
                }
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    // DELETE
    fun deleteUser(username: String, onResult: (Boolean) -> Unit = {}) {
        val key = sanitizeKey(username)
        database.child(key).removeValue()
            .addOnSuccessListener {
                registeredUsers.removeAll { it.username == username }
                auth.currentUser?.delete()
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    fun addUser(user: User): Boolean {
        if (!userExists(user.username)) {
            registeredUsers.add(user)
            val key = sanitizeKey(user.username)
            database.child(key).setValue(user)
            auth.createUserWithEmailAndPassword(formatAuthEmail(user.username), user.pass)
            return true
        }
        return false
    }

    fun getUser(username: String): User? {
        return registeredUsers.find { it.username == username }
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

    fun signOut() {
        auth.signOut()
    }
}

val userRepository = UserRepository()