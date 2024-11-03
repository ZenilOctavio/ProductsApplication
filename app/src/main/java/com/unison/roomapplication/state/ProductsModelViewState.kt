package com.unison.roomapplication.state

import androidx.compose.runtime.mutableStateListOf
import com.unison.roomapplication.model.Producto

data class ProductsModelViewState (
    val productos: List<Producto> = emptyList()
)
