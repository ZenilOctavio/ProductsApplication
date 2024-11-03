package com.unison.roomapplication.view_models

import android.util.Log
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unison.roomapplication.model.Producto
import com.unison.roomapplication.room.ProductsDatabaseDao
import com.unison.roomapplication.state.ProductsModelViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import com.unison.roomapplication.navigation.NavDestinations
import com.unison.roomapplication.snackbar.SnackbarManager
import com.unison.roomapplication.utils.stringToDouble


class ProductosViewModel(
    private val dao: ProductsDatabaseDao,
    val snackbarManager: SnackbarManager,
    val navController: NavController
) : ViewModel() {

   fun getProducts() = dao.getAllProducts().asLiveData(viewModelScope.coroutineContext)
   fun getProductById(id: Int) = dao.getProductById(id)




    fun onEvent(event: ProductoEvent){
        when(event){
            is ProductoEvent.CreateProducto -> {
                val nombre = event.nombre
                val descripcion = event.descripcion
                val precio = stringToDouble(event.precio)
                val fecha = event.fecha

                if (nombre.isBlank() || descripcion.isBlank() || precio == null || fecha == null){
                    return
                }

                val producto = Producto(
                    name = nombre,
                    description = descripcion,
                    price = precio,
                    date = fecha)

                viewModelScope.launch {
                    try{
                        dao.insertProduct(producto)
                        Log.d("ProductoViewModel", "Producto creado: $producto")
                        snackbarManager.simpleSnackbar("Producto creado")
                        navController.navigate(NavDestinations.ProudctsList.route)
                    }
                    catch(e: Exception){
                        snackbarManager.simpleSnackbar("No se pudo crear el producto")

                    }
                }


            }

            is ProductoEvent.DeleteProducto -> {

                viewModelScope.launch {
                    dao.deleteProduct(event.producto)
                }
            }

            is ProductoEvent.UpdateProducto -> {

                Log.d("ProductoViewModel", "Producto actualizado: ${event.producto}")
                Log.d("ProductoViewModel", "Producto original: ${event.productoOriginal}")

                val nombre = event.producto.name
                val descripcion = event.producto.description
                val precio = event.producto.price
                val fecha = event.producto.date

                if (nombre.isBlank() || descripcion.isBlank() || precio == null || fecha == null){
                    return
                }
                if(nombre == event.productoOriginal.name && descripcion == event.productoOriginal.description && precio == event.productoOriginal.price && fecha == event.productoOriginal.date) {
                    Log.d("ProductoViewModel", "No se realizaron cambios en el producto")
                    return
                }

                val producto = Producto(
                    id = event.productoOriginal.id,
                    name = nombre,
                    description = descripcion,
                    price = precio,
                    date = fecha)

                viewModelScope.launch {
                    dao.updateProduct(producto)
                    snackbarManager.simpleSnackbar("Producto actualizado")
                    navController.navigate(NavDestinations.ProudctsList.route)
                }

            }
        }
    }
}
