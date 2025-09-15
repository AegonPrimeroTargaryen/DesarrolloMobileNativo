package com.example.myapp.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.screens.ForgotPassword
import com.example.myapp.screens.LoginApp
import com.example.myapp.screens.Register
import com.example.myapp.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(
            Routes.LOGIN
        ) {
            LoginApp(
                onGoRegister = {
                    navController.navigate(Routes.REGISTER)
                },
                onGoForgot = {
                    navController.navigate(Routes.FORGOT)
                }
            )
        }
        composable(
            Routes.REGISTER
        ) {
            Register(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            Routes.FORGOT
        ) {
            ForgotPassword(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}