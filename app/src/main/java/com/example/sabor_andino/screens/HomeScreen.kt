package com.example.sabor_andino.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sabor_andino.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "¡Hola, bienvenido!",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "¿Qué deseas hoy?",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(40.dp))

        Card(
            onClick = { navController.navigate(Screen.Menu.route) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Box(modifier = Modifier.padding(24.dp)) {
                Text("🍽️  Ver Menú", style = MaterialTheme.typography.titleMedium)
            }
        }

        Card(
            onClick = { navController.navigate(Screen.Pedido.route) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Box(modifier = Modifier.padding(24.dp)) {
                Text("📋  Mi Pedido", style = MaterialTheme.typography.titleMedium)
            }
        }

        Card(
            onClick = { navController.navigate(Screen.Profile.route) },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Box(modifier = Modifier.padding(24.dp)) {
                Text("👤  Mi Perfil", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}