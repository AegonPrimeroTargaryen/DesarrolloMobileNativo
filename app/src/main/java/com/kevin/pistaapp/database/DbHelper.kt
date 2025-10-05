package com.kevin.pistaapp.database

import android.content.Context
import android.util.Patterns
import com.kevin.pistaapp.utils.RegisterHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DbHelper {

    data class LoginResult(
        val ok: Boolean,
        val mensaje: String? = null,
        val usuario: UsuarioEntity? = null
    )

    fun validarRegistro(
        context: Context,
        nombre: String,
        correo: String,
        password: String,
        callback: (RegisterHelper.ValidationResult) -> Unit
    ) {
        val errores = mutableListOf<String>()

        if (nombre.isBlank()) errores += "El nombre es obligatorio."

        if (correo.isBlank()) {
            errores += "El correo es obligatorio."
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            errores += "El correo no tiene un formato válido."
        }

        if (password.isBlank()) {
            errores += "La contraseña es obligatoria."
        }

        // 🔹 Validar correo único en la base de datos (consulta en Room)
        if (errores.isEmpty()) {
            val db = DataBaseApp.getDatabase(context)
            CoroutineScope(Dispatchers.IO).launch {
                val usuarioExistente = db.usuarioDao().buscarPorCorreo(correo)
                if (usuarioExistente != null) {
                    callback(RegisterHelper.ValidationResult(false, listOf("El correo ya está registrado.")))
                } else {
                    callback(RegisterHelper.ValidationResult(true))
                }
            }
        } else {
            callback(RegisterHelper.ValidationResult(false, errores))
        }
    }

    fun guardarRegistro(
        context: Context,
        nombre: String,
        correo: String,
        password: String,
        callback: (RegisterHelper.ValidationResult) -> Unit
    ) {
        validarRegistro(
            context,
            nombre,
            correo,
            password
        ) { validacion ->
            if (!validacion.ok) {
                callback(validacion)
                return@validarRegistro
            }

            val usuario = UsuarioEntity(
                nombre = nombre,
                correo = correo,
                password = password
            )

            val db = DataBaseApp.getDatabase(context)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    db.usuarioDao().insertar(usuario)

                    withContext(Dispatchers.Main) {
                        callback(RegisterHelper.ValidationResult(true))
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        callback(RegisterHelper.ValidationResult(false, listOf("Error BD: ${e.message}")))
                    }
                }
            }
        }
    }


    fun autenticar(
        context: Context,
        correo: String,
        password: String,
        callback: (LoginResult) -> Unit
    ) {
        val correoNorm = correo.trim().lowercase()
        val db = DataBaseApp.getDatabase(context)

        CoroutineScope(Dispatchers.IO).launch {
            val usuario = db.usuarioDao().buscarPorCorreo(correoNorm)

            if (usuario == null) {
                withContext(Dispatchers.Main) {
                    callback(LoginResult(false, "Correo no existe"))
                }
            } else if (usuario.password != password) {
                withContext(Dispatchers.Main) {
                    callback(LoginResult(false, "Contraseña incorrecta"))
                }
            } else {

                val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                prefs.edit()
                    .putBoolean("isLogged", true)
                    .putString("correo", usuario.correo)
                    .putString("nombre", usuario.nombre)
                    .apply()

                withContext(Dispatchers.Main) {
                    callback(LoginResult(true, usuario = usuario))
                }
            }
        }
    }
}