package com.example.saborandino.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saborandino.navigation.Screen

data class Plato(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String
)

val platos = listOf(
    Plato(1, "Ceviche Clásico", "Fresco ceviche de pescado con limón y ají.", 25.0, "Entradas"),
    Plato(2, "Causa Limeña", "Capas de papa amarilla con pollo y mayonesa.", 18.0, "Entradas"),
    Plato(3, "Lomo Saltado", "Clásico lomo con verduras y papas fritas.", 35.0, "Platos de Fondo"),
    Plato(4, "Ají de Gallina", "Pollo en crema de ají amarillo con arroz.", 30.0, "Platos de Fondo"),
    Plato(5, "Mazamorra Morada", "Postre tradicional de maíz morado.", 12.0, "Postres"),
    Plato(6, "Chicha Morada", "Bebida refrescante de maíz morado.", 8.0, "Bebidas")
)

val categorias = listOf("Todos", "Entradas", "Platos de Fondo", "Postres", "Bebidas")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

    val platosFiltrados = if (categoriaSeleccionada == "Todos") platos
    else platos.filter { it.categoria == categoriaSeleccionada }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categorias) { cat ->
                    FilterChip(
                        selected = categoriaSeleccionada == cat,
                        onClick = { categoriaSeleccionada = cat },
                        label = { Text(cat) }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(platosFiltrados) { plato ->
                    Card(
                        onClick = {
                            navController.navigate(Screen.Detail.createRoute(plato.id))
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(plato.nombre, style = MaterialTheme.typography.titleMedium)
                            Text(plato.descripcion, style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("S/ ${plato.precio}", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}