package com.kevin.pistaapp.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreen_usuarioValido() {
        var loginSuccess = false

        composeTestRule.setContent {
            LoginApp(
                onGoRegister = {},
                onGoForgot = {},
                onLoginSuccess = { loginSuccess = true }
            )
        }

        composeTestRule.onNodeWithTag("userInput").performTextInput("mi@correo.cl")
        composeTestRule.onNodeWithTag("passInput").performTextInput("123")

        composeTestRule.onNodeWithTag("loginBtn").performClick()

        assert(loginSuccess)
    }

    @Test
    fun loginScreen_usuarioInvalido() {
        var loginSuccess = false

        composeTestRule.setContent {
            LoginApp(
                onGoRegister = {},
                onGoForgot = {},
                onLoginSuccess = { loginSuccess = true }
            )
        }

        composeTestRule.onNodeWithText("Usuario").performTextInput("otro@correo.cl")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("123")

        composeTestRule.onNodeWithText("Iniciar sesión").performClick()

        assert(!loginSuccess)
    }
}