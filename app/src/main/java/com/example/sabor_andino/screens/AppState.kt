package com.example.sabor_andino.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class ItemPedido(val plato: Plato, val cantidad: Int)

object AppState {
    // 3. Campo para nombre de usuario separado del correo
    var correoUsuario by mutableStateOf("")
    var nombreUsuario by mutableStateOf("")
    
    val carrito = mutableStateListOf<ItemPedido>()

    // 5. Propiedad calculada para la cantidad total de items
    val cantidadTotalItems: Int get() = carrito.sumOf { it.cantidad }

    fun totalPedido(): Double = carrito.sumOf { it.plato.precio * it.cantidad }

    fun agregarAlCarrito(plato: Plato, cantidad: Int) {
        val index = carrito.indexOfFirst { it.plato.id == plato.id }
        if (index >= 0) {
            val existente = carrito[index]
            carrito[index] = existente.copy(cantidad = existente.cantidad + cantidad)
        } else {
            carrito.add(ItemPedido(plato, cantidad))
        }
    }

    // 4. Función para reducir cantidad (decremento)
    fun reducirCantidad(platoId: Int) {
        val index = carrito.indexOfFirst { it.plato.id == platoId }
        if (index >= 0) {
            val existente = carrito[index]
            if (existente.cantidad > 1) {
                carrito[index] = existente.copy(cantidad = existente.cantidad - 1)
            } else {
                // Elimina automáticamente si llega a 0
                carrito.removeAt(index)
            }
        }
    }

    // 1. Función para eliminar un plato por completo
    fun eliminarDelCarrito(platoId: Int) {
        carrito.removeAll { it.plato.id == platoId }
    }

    // 2. Función para limpiar todo el carrito
    fun limpiarCarrito() {
        carrito.clear()
    }
}
