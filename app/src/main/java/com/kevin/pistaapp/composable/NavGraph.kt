package com.kevin.pistaapp.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kevin.pistaapp.screens.ForgotPassword
import com.kevin.pistaapp.screens.LoginApp
import com.kevin.pistaapp.screens.Register
import com.kevin.pistaapp.navigation.Routes
import com.kevin.pistaapp.screens.DetalleScreen
import com.kevin.pistaapp.screens.HomeScreen
import com.kevin.pistaapp.screens.PistasScreen

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
                onBack = { navController.popBackStack() },
                onNavigateToDetalle = {navController.navigate(Routes.DETALLE)},
                onNavigateToPistas = {navController.navigate(Routes.PISTAS)},
                onNavigateToLogin = {navController.navigate(Routes.LOGIN)}
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

        composable(
            Routes.PISTAS
        ) {
            PistasScreen (
                onBack = { navController.popBackStack() },
            )
        }
    }
}