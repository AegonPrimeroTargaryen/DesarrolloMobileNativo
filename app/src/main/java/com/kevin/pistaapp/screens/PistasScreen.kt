package com.kevin.pistaapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kevin.pistaapp.composable.PistasContentProvider
import com.kevin.pistaapp.shaders.TopBar

@Composable
fun PistasScreen(
    onBack: () -> Unit,
){
    Scaffold(
        topBar = { TopBar("Mi APP", true, onBack) }
    ) {
        padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ){
            PistasContentProvider()
        }

    }
}

@Preview
@Composable()
fun PreviewPistasScreen() {
    PistasScreen(onBack = {})
}