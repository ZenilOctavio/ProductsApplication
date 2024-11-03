package com.unison.roomapplication.views

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.unison.roomapplication.R
import com.unison.roomapplication.model.Producto
import com.unison.roomapplication.navigation.NavDestinations
import com.unison.roomapplication.utils.stringToDouble
import com.unison.roomapplication.view_models.ProductoEvent
import com.unison.roomapplication.view_models.ProductosViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditProductForm(product: Producto, navController: NavController, viewModel: ProductosViewModel, modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bone))
    ){
        Column {
            EditProductFormHeader(navController)
            EditProductFormBody(
                product = product,
                onSaveProduct = { name: String, description: String, price: String, date: String ->

                    viewModel.onEvent(ProductoEvent.UpdateProducto(Producto(product.id, name, description, price.toDouble(), date), product))


//                    navController.navigate(NavDestinations.ProudctsList.route)

                },
                onCancel = {
                    navController.navigate(NavDestinations.ProudctsList.route)
                },
                modifier = Modifier.padding(top = 80.dp)
            )
            Snackbar(content = { Text(text = "Producto editado")}, )
        }
    }
}

@Composable
fun EditProductFormHeader(navController: NavController,modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f),
                shape = RoundedCornerShape(
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                )
            )
            .height(215.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
            ,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            GoBackArrow(navController)
            Column (
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Editar producto",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 25.sp,
                    letterSpacing = -1.sp,
                )
                Text(
                    text = "Por favor ingrese los datos del producto. Mantener tu inventario organizado nos ayuda a mostrar las mejores opciones de compra.",
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.5f),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 5.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductFormBody(
    product: Producto,
    onSaveProduct: (name: String, description: String, price: String, date: String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val dateValues = product.date.split("/")
    val year = dateValues[2].toInt()
    val month = dateValues[1].removePrefix("0").toInt() - 1
    val day = dateValues[0].removePrefix("0").toInt()

    val date = Date(year - 1900, month, day)

    var nombre by remember { mutableStateOf(product.name) }
    var descripcion by remember { mutableStateOf(product.description) }
    var precio by remember { mutableStateOf(product.price.toString()) }
    var fecha by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        OutlinedTextField(
            maxLines = 1,
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState(date.time)
        val selectedDate = datePickerState.selectedDateMillis?.let {
            convertMillisToDate(it)
        } ?: ""
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text("Fecha de registro") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = !showDatePicker }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            )

            if (showDatePicker) {
                Popup(
                    onDismissRequest = { showDatePicker = false },
                    alignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 64.dp)
                            .shadow(elevation = 4.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                    }
                }
            }
        }
        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = precio,
            onValueChange = {
                val result = stringToDouble(it)

                if(result != null) precio = it
            },
            label = { Text("Precio del producto") },
//                trailingIcon = Image(imageVector = , contentDescription = ""),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        OutlinedTextField(
            minLines = 4,
            value = descripcion,
            onValueChange = {descripcion = it},
            label = { Text("Descripción del producto") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )

        Row (
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = {
                    onSaveProduct(nombre, descripcion, precio, selectedDate)

                },
                //colors = ButtonColors(containerColor = colorResource(id = R.color.purple), contentColor = colorResource(id = R.color.bone), disabledContentColor = Color.Gray, disabledContainerColor = Color.Gray)
            ) {
                Text(text = "Guardar")
            }
            OutlinedButton(
                onClick = onCancel,
                //colors = ButtonColors(containerColor = Color.White, contentColor = colorResource(id = R.color.purple), disabledContentColor = Color.Gray, disabledContainerColor = Color.Gray)
            ) {
                Text(text = "Cancelar")
            }
        }

    }

}