package com.unison.roomapplication.components.products_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unison.roomapplication.R
import com.unison.roomapplication.dialogs.DialogWithIcon
import com.unison.roomapplication.model.Producto

@Composable
fun ProductoItem(product: Producto, onEdit: (producto: Producto) -> Unit, onDelete: (producto: Producto) -> Unit, modifier: Modifier = Modifier) {

    val backgroundShape = RoundedCornerShape(12.dp)

    val (isModalShown, setModalShown) = remember { mutableStateOf(false) }

    if(isModalShown){
        DialogWithIcon(
            onDismissRequest = { setModalShown(false)},
            onConfirmation = {
                try {
                    onDelete(product)
                    setModalShown(false)
                } catch (e: Exception) {
                    e.printStackTrace()
                    setModalShown(false)
                }
             },
            dialogTitle = "Eliminar producto",
            dialogText = "¿Estas seguro de eliminar este producto?",
            icon = Icons.Filled.Delete,
            iconDescription = "Eliminar producto",
            confirmationText = "Si, eliminar",
            dismissText = "Cancelar"
        )
    }

    Box(Modifier.padding(top=5.dp)){
        Box (
            modifier = Modifier
                .shadow(
                    elevation = 15.dp,
                    spotColor = Color.Black,
                    ambientColor = Color.Blue,
                    shape = backgroundShape
                )
                .background(MaterialTheme.colorScheme.secondaryContainer, shape = backgroundShape)
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(120.dp)

            ,
            contentAlignment = Alignment.Center
        ){
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
//                Box(
//                    modifier = Modifier
//                        .width(100.dp)
//                        .height(100.dp)
//                        .background(
//                            MaterialTheme.colorScheme.surfaceBright,
//                            shape = RoundedCornerShape(10.dp)
//                        )
//                )

                Column (
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(300.dp)
                ) {
                    Column(modifier = Modifier){
                        Text(
                            text = product.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            maxLines = 1,
                            softWrap = true,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            modifier = Modifier.padding(top = 5.dp),
                            text = "$${product.price - .01}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Column{
                        Text(
                            text = product.description,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            softWrap = true,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
                        )
                    }

                }
                Column{
                    IconButton(onClick = { setModalShown(true) }) {
                        Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
                    }
                    IconButton(onClick = { onEdit(product) }) {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Delete")
                    }
                }
            }
        }
    }



}