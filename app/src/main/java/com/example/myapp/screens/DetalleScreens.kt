package com.example.myapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.models.Piloto
import com.example.myapp.shaders.TopBar

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    piloto: String?,
    onBack: () -> Unit
){
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
                Text("Bienvenido Detalle: ",
                    style = MaterialTheme.typography.titleLarge)
                if (piloto != null) {
                    Text(piloto,
                        style = MaterialTheme.typography.titleLarge)
                }

            }
        }
    }
}

//@Preview
//@Composable()
//fun PreviewDetalleScreen() {
//    DetalleScreen(onBack = {}, piloto = Piloto("","",0))
//}