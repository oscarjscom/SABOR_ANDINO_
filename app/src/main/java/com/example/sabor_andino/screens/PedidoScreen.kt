package com.example.sabor_andino.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sabor_andino.navigation.Screen
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoScreen(navController: NavController) {
    val carrito = AppState.carrito
    val total = AppState.totalPedido()
    
    var showPaymentDialog by remember { mutableStateOf(false) }
    var showPaidMessage by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Pedido") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        if (carrito.isEmpty()) {
            EmptyPedidoState(
                onExploreMenu = {
                    navController.navigate(Screen.Menu.route) {
                        popUpTo(Screen.Home.route)
                    }
                },
                modifier = Modifier.padding(padding)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Spacer(modifier = Modifier.height(16.dp)) }

                items(carrito) { item ->
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Imagen del plato
                            AsyncImage(
                                model = item.plato.imageUrl,
                                contentDescription = item.plato.nombre,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            // Información central
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.plato.nombre,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "S/ ${"%.2f".format(item.plato.precio)}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                
                                Spacer(modifier = Modifier.height(4.dp))

                                // Controles de cantidad
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    FilledIconButton(
                                        onClick = { AppState.reducirCantidad(item.plato.id) },
                                        modifier = Modifier.size(32.dp),
                                        colors = IconButtonDefaults.filledIconButtonColors(
                                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                                        )
                                    ) {
                                        Icon(
                                            Icons.Default.Remove,
                                            contentDescription = "Menos",
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                    
                                    Text(
                                        text = "${item.cantidad}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Medium
                                    )
                                    
                                    FilledIconButton(
                                        onClick = { AppState.agregarAlCarrito(item.plato, 1) },
                                        modifier = Modifier.size(32.dp),
                                        colors = IconButtonDefaults.filledIconButtonColors(
                                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                                        )
                                    ) {
                                        Icon(
                                            Icons.Default.Add,
                                            contentDescription = "Más",
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            }

                            // Botón eliminar
                            IconButton(
                                onClick = { AppState.eliminarDelCarrito(item.plato.id) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Total del pedido
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total del pedido",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "S/ ${"%.2f".format(total)}",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Botón confirmar
                    Button(
                        onClick = { showPaymentDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Confirmar Pedido", style = MaterialTheme.typography.labelLarge)
                    }

                    if (showPaidMessage) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "✅ ¡Pedido confirmado! Gracias por tu compra.",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                
                item { Spacer(modifier = Modifier.height(32.dp)) }
            }
        }
    }

    PagoBottomSheet(
        visible = showPaymentDialog,
        total = total,
        onDismiss = { showPaymentDialog = false },
        onConfirmar = {
            showPaymentDialog = false
            showPaidMessage = true
            AppState.limpiarCarrito()
        }
    )
}

@Composable
fun EmptyPedidoState(
    onExploreMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(120.dp),
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Tu carrito está vacío",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(
            text = "¿Aún no has probado nuestras delicias? Explora el menú y descubre el auténtico sabor andino.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onExploreMenu,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = "Explorar Menú",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagoBottomSheet(
    visible: Boolean,
    total: Double,
    onDismiss: () -> Unit,
    onConfirmar: () -> Unit
) {
    if (visible) {
        var metodoPago by remember { mutableStateOf("tarjeta") }

        // Estados para Tarjeta
        var numeroTarjeta by remember { mutableStateOf("") }
        var vencimiento by remember { mutableStateOf("") }
        var cvv by remember { mutableStateOf("") }
        var nombreTarjeta by remember { mutableStateOf("") }

        // Validaciones MM/AA
        val mesStr = if (vencimiento.length >= 2) vencimiento.substring(0, 2) else ""

        val mesValido = mesStr.toIntOrNull()?.let { it in 1..12 } == true
        val fechaValida = vencimiento.length == 5 && mesValido

        val tarjetaValida = numeroTarjeta.length == 16 && 
                           fechaValida && 
                           cvv.length == 3 && 
                           nombreTarjeta.isNotBlank()

        // Estado para Efectivo
        var montoPago by remember { mutableStateOf("") }
        val montoMostrado = if (montoPago.isEmpty()) "" else montoPago
        val montoNum = montoPago.toDoubleOrNull() ?: 0.0
        val montoValido = montoNum >= total

        val confirmEnabled = when (metodoPago) {
            "tarjeta" -> tarjetaValida
            "yape"    -> true
            "efectivo" -> montoValido
            else      -> false
        }

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Método de Pago",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                // OPCIÓN TARJETA
                PaymentOption(
                    nombre = "Tarjeta de Crédito/Débito",
                    icon = Icons.Default.CreditCard,
                    selected = metodoPago == "tarjeta",
                    onSelect = { metodoPago = "tarjeta" }
                )
                AnimatedVisibility(visible = metodoPago == "tarjeta") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = numeroTarjeta,
                            onValueChange = { if (it.length <= 16) numeroTarjeta = it },
                            label = { Text("Número de tarjeta") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            maxLines = 1
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = vencimiento,
                                onValueChange = { input ->
                                    val soloNumeros = input.filter { it.isDigit() }.take(4)
                                    
                                    val formatted = when {
                                        soloNumeros.length <= 2 -> {
                                            val mesNum = soloNumeros.toIntOrNull() ?: 0
                                            when {
                                                soloNumeros.length == 2 && mesNum > 12 -> "12"
                                                soloNumeros.length == 2 && mesNum < 1 
                                                    && soloNumeros == "00" -> "01"
                                                else -> soloNumeros
                                            }
                                        }
                                        else -> {
                                            val mesStr = soloNumeros.substring(0, 2)
                                            val mesNum = mesStr.toIntOrNull() ?: 1
                                            val mesCorregido = when {
                                                mesNum < 1  -> "01"
                                                mesNum > 12 -> "12"
                                                else -> mesStr
                                            }
                                            val anio = soloNumeros.substring(2)
                                            "$mesCorregido/$anio"
                                        }
                                    }
                                    vencimiento = formatted
                                },
                                label = { Text("MM/AA") },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                isError = mesStr.length == 2 && !mesValido,
                                supportingText = {
                                    if (mesStr.length == 2 && !mesValido) {
                                        Text("Mes inválido (01-12)", color = MaterialTheme.colorScheme.error)
                                    }
                                },
                                maxLines = 1
                            )
                            OutlinedTextField(
                                value = cvv,
                                onValueChange = { if (it.length <= 3 && it.all { char -> char.isDigit() }) cvv = it },
                                label = { Text("CVV") },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                visualTransformation = PasswordVisualTransformation(),
                                isError = cvv.isNotEmpty() && cvv.length < 3,
                                supportingText = {
                                    if (cvv.isNotEmpty() && cvv.length < 3) {
                                        Text("El CVV debe tener 3 dígitos", color = MaterialTheme.colorScheme.error)
                                    }
                                },
                                maxLines = 1
                            )
                        }
                        OutlinedTextField(
                            value = nombreTarjeta,
                            onValueChange = { nombreTarjeta = it },
                            label = { Text("Nombre en la tarjeta") },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 1
                        )
                    }
                }

                // OPCIÓN YAPE
                PaymentOption(
                    nombre = "Yape / Plin",
                    icon = Icons.Default.QrCodeScanner,
                    selected = metodoPago == "yape",
                    onSelect = { metodoPago = "yape" }
                )
                AnimatedVisibility(visible = metodoPago == "yape") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Yapea o plínea al siguiente número:", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            "987 654 321",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            "A nombre de: Sabor Andino",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            "Una vez pagado presiona Confirmar Pago",
                            style = MaterialTheme.typography.labelSmall,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }

                // OPCIÓN EFECTIVO
                PaymentOption(
                    nombre = "Efectivo",
                    icon = Icons.Default.Payments,
                    selected = metodoPago == "efectivo",
                    onSelect = { metodoPago = "efectivo" }
                )
                AnimatedVisibility(visible = metodoPago == "efectivo") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("¿Con cuánto vas a pagar?", style = MaterialTheme.typography.bodyMedium)
                        OutlinedTextField(
                            value = montoMostrado,
                            onValueChange = { input ->
                                // Solo dígitos, máximo 4 (hasta S/ 9999)
                                val soloDigitos = input.filter { it.isDigit() }.take(4)
                                montoPago = soloDigitos
                            },
                            prefix = { Text("S/ ") },
                            placeholder = { Text("Ej: 50, 100, 200...") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            maxLines = 1
                        )

                        Text(
                            "Ingresa el monto exacto en soles con el que pagarás",
                            style = MaterialTheme.typography.labelSmall
                        )

                        if (montoNum < total) {
                            Text(
                                "El monto debe ser mayor o igual a S/ ${"%.2f".format(total)}",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total a pagar", style = MaterialTheme.typography.bodyLarge)
                    Text(
                        "S/ ${"%.2f".format(total)}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onConfirmar,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = MaterialTheme.shapes.large,
                    enabled = confirmEnabled
                ) {
                    Text("Confirmar Pago", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun PaymentOption(nombre: String, icon: ImageVector, selected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = nombre, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))
        RadioButton(selected = selected, onClick = onSelect)
    }
}
