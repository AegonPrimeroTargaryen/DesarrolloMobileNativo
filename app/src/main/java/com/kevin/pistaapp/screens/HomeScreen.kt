package com.kevin.pistaapp.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kevin.pistaapp.shaders.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBack: () -> Unit,
    onNavigateToDetalle: () -> Unit = {},
    onNavigateToPistas: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
){
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val nombre = prefs.getString("nombre", null)

    Scaffold(
        topBar = { TopBar("Mi APP", false, onBack, onNavigateToDetalle, onNavigateToPistas, onNavigateToLogin) }
    ) {
        padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    "Bienvenido: $nombre",
                    style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@Preview
@Composable()
fun PreviewHomeScreen() {
    HomeScreen(onBack = {})
}