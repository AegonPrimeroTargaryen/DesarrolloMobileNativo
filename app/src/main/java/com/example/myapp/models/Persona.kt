package com.example.myapp.models

open class Persona(val nombre: String, val edad: Int) {
    fun descrPersona(): String {
        return "$nombre tiene $edad años"
    }
}