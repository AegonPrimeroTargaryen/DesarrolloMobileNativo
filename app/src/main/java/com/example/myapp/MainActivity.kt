package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapp.composable.AppNav
import com.example.myapp.screens.LoginApp
import com.example.myapp.utils.RegisterHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                RegisterHelper.guardarRegistro("Kevin Molina", "tu@correo.cl", "123")
                AppNav()
            }
        }
    }
}

@Preview
@Composable
fun PreviewApp() {
    LoginApp(onGoForgot = {}, onGoRegister = {}, onLoginSuccess = {})
}
