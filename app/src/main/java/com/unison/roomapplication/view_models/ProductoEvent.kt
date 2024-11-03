package com.unison.roomapplication.view_models

import com.unison.roomapplication.model.Producto

sealed interface ProductoEvent {
    data class CreateProducto(val nombre: String, val descripcion: String, val precio: String, val fecha: String): ProductoEvent
    data class DeleteProducto (val producto: Producto): ProductoEvent
    data class UpdateProducto(val producto: Producto, val productoOriginal: Producto): ProductoEvent

}