package com.example.sabor_andino.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class ItemPedido(val plato: Plato, val cantidad: Int)

object AppState {
    var correoUsuario by mutableStateOf("")
    val carrito = mutableStateListOf<ItemPedido>()

    fun agregarAlCarrito(plato: Plato, cantidad: Int) {
        val index = carrito.indexOfFirst { it.plato.id == plato.id }
        if (index >= 0) {
            val existente = carrito[index]
            carrito[index] = existente.copy(cantidad = existente.cantidad + cantidad)
        } else {
            carrito.add(ItemPedido(plato, cantidad))
        }
    }

    fun totalPedido(): Double = carrito.sumOf { it.plato.precio * it.cantidad }
}
