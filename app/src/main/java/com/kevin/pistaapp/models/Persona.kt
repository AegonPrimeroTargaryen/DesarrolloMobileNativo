package com.kevin.pistaapp.models

import kotlinx.serialization.Serializable

@Serializable
open class Persona(val nombre: String, val edad: Int) {
    fun descrPersona(): String {
        return "$nombre tiene $edad años"
    }
}