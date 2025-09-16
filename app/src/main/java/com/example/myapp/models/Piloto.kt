package com.example.myapp.models

class Piloto(val experiencia: String, nombre: String, edad: Int) : Persona(nombre, edad) {
    fun descrExpe(): String {
        return "Piloto con $experiencia de experiencia en Karting"
    }
}