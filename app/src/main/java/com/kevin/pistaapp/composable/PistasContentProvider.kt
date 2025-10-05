package com.kevin.pistaapp.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kevin.pistaapp.models.Pista

@Composable
fun PistasContentProvider() {
    val pistas: List<Pista> = listOf(
        Pista("LastLap", 5f, 250.5f, 0),
        Pista("SpeedLampa", 5.5f, 260f, 0),
        Pista("Karting Pirque", 5.2f, 270f, 0),
        Pista("Km42Paine", 6f, 350f, 0),
        Pista("SpeedTrack", 3f, 200f, 0),
    )
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items( pistas) { item ->
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .size(width = 300.dp, height = 300.dp)
            ) {
                var vueltas by remember { mutableStateOf(item.vueltas) }
                Text(
                    text = "Pista: "+ item.nombre,
                    modifier = Modifier
                        .padding(8.dp),
                )
                Text(
                    text = "Ancho: "+ item.ancho +" mts",
                    modifier = Modifier
                        .padding(16.dp),
                )
                Text(
                    text = "Largo: "+ item.largo+" mts",
                    modifier = Modifier
                        .padding(16.dp),
                )
                Text(
                    text = "Vueltas: $vueltas",
                    modifier = Modifier
                        .padding(16.dp),
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( 8.dp)
                ){
                    // <-- 3. Ahora le pasamos la LÓGICA al ButtonEvent.
                    ButtonEvent(
                        onClick = { if (vueltas > 0) vueltas-- }, // Le decimos: "Al hacer clic, ejecuta esto"
                        nombreBtn = "- Vueltas"
                    )
                    ButtonEvent(
                        onClick = { vueltas++ }, // Le decimos: "Al hacer clic, ejecuta esto"
                        nombreBtn = "+ Vueltas"
                    )
                }

            }
        }
    }
}

@Composable
fun ButtonEvent(
    onClick: () -> Unit, // <-- 1. Recibe una función lambda que no devuelve nada.
    nombreBtn: String
) {
    Button(
        onClick = onClick, // <-- 2. Llama a esa función cuando se hace clic en el botón.
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Text(nombreBtn)
    }
}

@Preview
@Composable
fun PreviewPistasContentProvider() {
    PistasContentProvider()
}