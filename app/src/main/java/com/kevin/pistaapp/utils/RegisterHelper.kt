package com.kevin.pistaapp.utils

import android.content.Context
import com.kevin.pistaapp.database.DbHelper

object RegisterHelper {

    data class ValidationResult(
        val ok: Boolean,
        val errores: List<String> = emptyList()
    )

    /** Función genérica para guardar datos */
    fun guardarRegistro(context: Context, nombreCompleto: String, correo: String, password: String, callback: (ValidationResult) -> Unit) : ValidationResult{

        DbHelper.validarRegistro(context, nombreCompleto, correo, password){ validation ->
            if (!validation.ok) {
                callback(validation)
                return@validarRegistro
            }
            DbHelper.guardarRegistro(context, nombreCompleto, correo, password){
                callback(it)
                return@guardarRegistro
            }

        }
        return ValidationResult(false)
    }

    /**
     * Autentica por correo y password contra 'registros'.
     * Normaliza el correo (trim y lowercase).
     */
    fun autenticar(context: Context, correo: String, password: String, callback: (DbHelper.LoginResult) -> Unit): DbHelper.LoginResult {
        DbHelper.autenticar(context, correo, password) { result ->
            callback(result)
            return@autenticar
        }
        return DbHelper.LoginResult(false)
    }

}