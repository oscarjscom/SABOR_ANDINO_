package com.example.sabor_andino.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sabor_andino.navigation.Screen

data class Plato(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoria: String,
    val imageUrl: String,
    val esMasVendido: Boolean = false
)

val platos = listOf(
    Plato(1, "Ceviche Clásico", "Fresco ceviche de pescado con limón y ají.", 25.0, "Entradas", "https://img.freepik.com/foto-gratis/primer-plano-ceviche-pescado-peruano-servido-bol-blanco_181624-38615.jpg", esMasVendido = true),
    Plato(2, "Causa Limeña", "Capas de papa amarilla con pollo y mayonesa.", 18.0, "Entradas", "https://img.freepik.com/foto-gratis/causa-limena-pollo-comida-peruana_127032-1981.jpg"),
    Plato(3, "Lomo Saltado", "Clásico lomo con verduras y papas fritas.", 35.0, "Platos de Fondo", "https://img.freepik.com/foto-gratis/lomo-saltado-tradicional-comida-peruana_127032-2051.jpg", esMasVendido = true),
    Plato(4, "Ají de Gallina", "Pollo en crema de ají amarillo con arroz.", 30.0, "Platos de Fondo", "https://img.freepik.com/foto-gratis/aji-gallina-comida-peruana_127032-2015.jpg"),
    Plato(5, "Mazamorra Morada", "Postre tradicional de maíz morado.", 12.0, "Postres", "https://img.freepik.com/foto-gratis/mazamorra-morada-postre-peruano-tradicional_127032-2105.jpg"),
    Plato(6, "Chicha Morada", "Bebida refrescante de maíz morado.", 8.0, "Bebidas", "https://img.freepik.com/foto-gratis/chicha-morada-bebida-tradicional-peruana_127032-2150.jpg")
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
                    val icon = when (cat) {
                        "Entradas" -> Icons.Default.Restaurant
                        "Platos de Fondo" -> Icons.Default.RestaurantMenu
                        "Postres" -> Icons.Default.Fastfood
                        "Bebidas" -> Icons.Default.LocalBar
                        else -> null
                    }
                    FilterChip(
                        selected = categoriaSeleccionada == cat,
                        onClick = { categoriaSeleccionada = cat },
                        label = { Text(cat) },
                        leadingIcon = icon?.let {
                            { Icon(it, contentDescription = null, modifier = Modifier.size(18.dp)) }
                        }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(platosFiltrados) { plato ->
                    ElevatedCard(
                        onClick = {
                            navController.navigate(Screen.Detail.createRoute(plato.id))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .height(IntrinsicSize.Min)
                            ) {
                                // Imagen del plato
                                Box(
                                    modifier = Modifier
                                        .size(110.dp)
                                        .background(
                                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                            shape = MaterialTheme.shapes.medium
                                        )
                                        .clip(MaterialTheme.shapes.medium),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = plato.imageUrl,
                                        contentDescription = plato.nombre,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                        placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                                        error = painterResource(id = android.R.drawable.ic_menu_report_image)
                                    )
                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = plato.nombre,
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = plato.descripcion,
                                            style = MaterialTheme.typography.bodyMedium,
                                            maxLines = 2,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    Text(
                                        text = "S/ ${"%.2f".format(plato.precio)}",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }

                            // Badge "Más vendido"
                            if (plato.esMasVendido) {
                                Surface(
                                    color = MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp),
                                    modifier = Modifier.align(Alignment.TopEnd)
                                ) {
                                    Text(
                                        text = "⭐ Más vendido",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}
