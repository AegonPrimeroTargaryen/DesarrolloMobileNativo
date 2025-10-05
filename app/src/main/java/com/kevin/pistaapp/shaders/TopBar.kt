package com.kevin.pistaapp.shaders

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showBack: Boolean,
    onBack: (() -> Unit)? = null,
    onToNavigateDetalle: (() -> Unit)? = null,
    onNavigateToPistas: (() -> Unit)? = null,
    onNavigateToLogin: (() -> Unit)? = null
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
            )
        },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = { onBack?.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Atrás"
                    )
                }
            } else {
                IconButton(
                    onClick = { expanded = true }
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menú")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ){
                    DropdownMenuItem(
                        text = {(Text("Pilotos"))},
                        onClick = {
                            expanded = false
                            onToNavigateDetalle?.invoke()
                        }
                    )
                    DropdownMenuItem(
                        text = {(Text("Pistas"))},
                        onClick = {
                            expanded = false
                            onNavigateToPistas?.invoke()
                        }
                    )
                    DropdownMenuItem(
                        text = {(Text("Cerrar Sesión"))},
                        onClick = {
                            val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                            prefs.edit()
                                .putBoolean("isLogged", false)
                                .apply()
                            expanded = false
                            onNavigateToLogin?.invoke()
                        }
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = { /* TODO: ajustes */ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Ajustes"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background
        )
    )
}