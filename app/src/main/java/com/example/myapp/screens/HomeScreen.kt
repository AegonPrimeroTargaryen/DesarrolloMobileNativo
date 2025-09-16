package com.example.myapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.models.Piloto
import com.example.myapp.shaders.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBack: () -> Unit,
    onNavigateToDetalle: (Piloto) -> Unit = {}
){
    val pilotos = listOf(
        Piloto("2 años de experiencia", "Kevin Molina", 31),
        Piloto("1 años de experiencia", "Alonso Molina", 12),
        Piloto("4 años de experiencia", "Jesus Torrejon", 32),
        Piloto("6 años de experiencia", "Samuel Santander", 34),
    )
    Scaffold(
        topBar = { TopBar("Mi APP", true, onBack) }
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
                Text("Bienvenido: ",
                    style = MaterialTheme.typography.titleLarge)

                ElevatedCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Pilotos", style = MaterialTheme.typography.titleMedium)
                        LazyColumn {
                            items(pilotos) { piloto ->
                                ListItem(
                                    headlineContent = { Text(piloto.nombre) },
                                    supportingContent = { Text(piloto.descrExpe()) },
                                    leadingContent = {
                                        Icon(
                                            Icons.Filled.Person,
                                            contentDescription = "Piloto",
                                            tint = Color.Blue
                                        )
                                    },
                                    modifier = Modifier.clickable {
                                        onNavigateToDetalle(piloto)
                                    }
                                )
                            }
                        }
                    }
                }


            }
        }
    }
}

@Preview
@Composable()
fun PreviewHomeScreen() {
    HomeScreen(onBack = {})
}