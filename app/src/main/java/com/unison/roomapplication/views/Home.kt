package com.unison.roomapplication.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unison.roomapplication.navigation.NavDestinations
import com.unison.roomapplication.R


@Composable
fun Home (navController: NavController, modifier: Modifier = Modifier) {
    HomeBody(navController)
}




@Composable
fun HomeBody(navController: NavController, modifier: Modifier = Modifier) {
    val buttonColors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.background,
        disabledContainerColor = MaterialTheme.colorScheme.tertiary,
        disabledContentColor = colorResource(id = R.color.bone)
    )



    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(top = 30.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")
            Text(
                text = "WiredManagement",
                letterSpacing = -2.sp,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(400.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 40.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Get Started",
                            color = colorResource(id = R.color.black),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,

                            )
                        Text(
                            text = "Don't ever, for any reason, do anything, to anyone, for any reason, ever, no matter what, no matter where, or who, or who you are with, or where you are going, or where you-ve been, ever, for any reason whatsoever.",
                            color = colorResource(id = R.color.black).copy(0.5f),
                            lineHeight = 16.sp
                        )
                    }

                    Column (modifier = Modifier
                        .padding(horizontal = 20.dp)) {

                        Button(modifier = Modifier.fillMaxWidth() ,onClick = { navController.navigate(NavDestinations.PresentationCard.route) }, colors = buttonColors) {
                            Text(text = "Presentation Card")
                        }
                        Button(modifier = Modifier.fillMaxWidth() ,onClick = { navController.navigate(NavDestinations.ProudctsList.route) }, colors = buttonColors) {
                            Text(text = "Products List")
                        }


                    }

                    Column {
                        HorizontalDivider(modifier = Modifier.fillMaxWidth())

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp), horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "Made by ",
                                fontWeight = FontWeight.Bold,
                                softWrap = true,
                                color = colorResource(
                                    id = R.color.black
                                ),
                                letterSpacing = -1.sp
                            )
                            Text(text= "Zenil López Octavio", color = colorResource(id = R.color.black), letterSpacing = -1.sp)
                        }
                    }

                }
            }
        }
    }

}


@Composable
fun HomeFooter(modifier: Modifier = Modifier) {

}
