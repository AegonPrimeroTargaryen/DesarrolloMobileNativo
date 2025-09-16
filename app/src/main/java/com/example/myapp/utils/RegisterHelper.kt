package com.example.myapp.utils

object RegisterHelper {

    val registros = mutableListOf<Map<String, String>>()

    lateinit var userLoged: LoginResult

    data class ValidationResult(
        val ok: Boolean,
        val errores: List<String> = emptyList()
    )

    data class LoginResult(
        val ok: Boolean,
        val mensaje: String? = null,
        val usuario: String? = null
    )

    /** Función genérica para guardar datos */
    fun guardarRegistro(nombreCompleto: String, correo: String, password: String) : ValidationResult{

        val registro = mapOf(
            "nombre" to nombreCompleto,
            "correo" to correo,
            "password" to password,
        )
        registros.add(registro)

        return ValidationResult(true)
    }

    /**
     * Autentica por correo y password contra 'registros'.
     * Normaliza el correo (trim y lowercase).
     */
    fun autenticar(correo: String, password: String): LoginResult {
        val correoNorm = correo.trim().lowercase()
        val user = registros.firstOrNull { it["correo"]?.trim()?.lowercase() == correoNorm && it["password"] == password}
            ?: return LoginResult(false, "Correo o Contraseña Incorrecta")

        userLoged = LoginResult(true, usuario = user["nombre"])
        return userLoged

    }

}