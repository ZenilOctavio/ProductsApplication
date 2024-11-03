package com.unison.roomapplication.navigation

import android.util.Log
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unison.roomapplication.model.Producto
import com.unison.roomapplication.room.ProductDatabase
import com.unison.roomapplication.snackbar.SnackbarManager
import com.unison.roomapplication.views.Home
import com.unison.roomapplication.views.PresentationCard
import com.unison.roomapplication.views.ProductsList
import com.unison.roomapplication.views.ProductsForm
import com.unison.roomapplication.view_models.ProductosViewModel
import com.unison.roomapplication.views.EditProductForm


@Composable
fun NavManager(modifier: Modifier = Modifier, snackbarManager: SnackbarManager) {
    val navController = rememberNavController()

    val db = ProductDatabase.getDatabase(LocalContext.current)
    val productosDao = db.productsDao()

    val productosViewModel = ProductosViewModel(productosDao, snackbarManager, navController)

    NavHost(navController = navController, startDestination = "home" ){
        composable(NavDestinations.Home.route) { Home(navController) }
        composable(NavDestinations.PresentationCard.route) { PresentationCard(navController) }
        composable(NavDestinations.ProudctsList.route) { ProductsList(productosViewModel, navController) }
        composable("${NavDestinations.ProductsEdit.route}/{productId}", arguments = listOf(
            androidx.navigation.navArgument("productId") { type = androidx.navigation.NavType.IntType }
        )) {
            Log.d("NavManager", "Producto editado: ${it.arguments?.getInt("productId")}")
            val productId = it.arguments?.getInt("productId") ?: 0
            val productoState = productosViewModel.getProductById(productId).collectAsState(initial = null)
            val producto = productoState.value
            if (producto != null)
            EditProductForm(producto, navController, productosViewModel)
        }
        composable(NavDestinations.ProductsForm.route,) { ProductsForm(navController, productosViewModel) }


    }
}
