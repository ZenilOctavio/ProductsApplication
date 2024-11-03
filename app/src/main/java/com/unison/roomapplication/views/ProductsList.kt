package com.unison.roomapplication.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.unison.roomapplication.R
import com.unison.roomapplication.components.products_list.ProductoItem
import com.unison.roomapplication.model.Producto
import com.unison.roomapplication.navigation.NavDestinations
import com.unison.roomapplication.view_models.ProductoEvent
import com.unison.roomapplication.view_models.ProductosViewModel

@Composable
fun ProductsList(viewModel: ProductosViewModel, navController: NavController, modifier: Modifier = Modifier) {
    var productsList by remember { mutableStateOf(listOf<Producto>()) }


    viewModel.getProducts().observeForever{
        productsList = it
    }



    fun deleteProduct(producto: Producto) {
        Log.d("ProductoViewModel", "Producto eliminado: ${producto.id}")
        viewModel.onEvent(ProductoEvent.DeleteProducto(producto))
    }

    Column (
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        ProductsListHeader(navController)
        ProductListBody(productsList, onDelete = { deleteProduct(it) }, navController)




    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
        ,
        contentAlignment = Alignment.BottomEnd
    ){
        FloatingActionButton(
            onClick = { navController.navigate(NavDestinations.ProductsForm.route)},
            containerColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(60.dp)) {
            Icon(
                imageVector = Icons.Sharp.Add,
                contentDescription = "Agregar",
                tint = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .fillMaxSize(),

                )
        }
    }

}


@Composable
fun ProductsListHeader(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.inversePrimary,
                shape = RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp)
            )
            .height(120.dp)
    ){
        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
                )
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)),
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)

                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

            Box{
                FloatingActionButton(
                    onClick = {  navController.navigate(NavDestinations.Home.route) },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape,
                    modifier = Modifier.offset(x = -130.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                        contentDescription = "Go back",
                        tint = MaterialTheme.colorScheme.surfaceTint,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    modifier = Modifier.offset(y = 20.dp),
                    text = "Productos",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    letterSpacing = -1.sp
                )
            }


            }
        }
    }

}

@Composable
fun ProductListBody(products: List<Producto>, onDelete: (producto: Producto) -> Unit, navController: NavController, modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if(products.isEmpty()){
                Text(
                    modifier = Modifier.padding(top = 100.dp),
                    text = "No hay productos",
                )
            }
            else {
                LazyColumn (
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ){
                    items(products) {
                        ProductoItem(it,
                            {
                                val id = it.id.toString()
                                Log.d("ProductoViewModel", "Producto editado: $id")
                                val direction = "${NavDestinations.ProductsEdit.route}/{productId}".replace(oldValue = "{productId}", newValue = id)
                                Log.d("ProductoViewModel", "direction: $direction")
                                navController.navigate(direction)
                            }, onDelete)
                    }
                }
            }
        }
    }
}

