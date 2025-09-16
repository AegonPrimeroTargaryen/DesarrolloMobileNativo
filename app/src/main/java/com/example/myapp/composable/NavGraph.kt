package com.example.myapp.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapp.models.Piloto
import com.example.myapp.screens.ForgotPassword
import com.example.myapp.screens.LoginApp
import com.example.myapp.screens.Register
import com.example.myapp.navigation.Routes
import com.example.myapp.screens.DetalleScreen
import com.example.myapp.screens.HomeScreen

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
                },
                onLoginSuccess = {
                    navController.navigate(Routes.HOME)
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

        composable(
            Routes.HOME
        ) {
            HomeScreen(
                onNavigateToDetalle = { piloto: Piloto ->
                    navController.navigate("detalle/$piloto")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.DETALLE,
            arguments = listOf(navArgument("piloto") { type = NavType.StringType })
        ) { backStack ->
            val json = backStack.arguments?.getString("piloto")
            DetalleScreen(
                piloto = json,
                onBack = { navController.popBackStack() }
            )
        }
    }
}