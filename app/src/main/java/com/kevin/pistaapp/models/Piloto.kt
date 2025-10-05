package com.kevin.pistaapp.models

import kotlinx.serialization.Serializable
@Serializable
class Piloto(val experiencia: String, nombre: String, edad: Int) : Persona(nombre, edad) {
    fun descrExpe(): String {
        return "Piloto con $experiencia de experiencia en Karting"
    }
}