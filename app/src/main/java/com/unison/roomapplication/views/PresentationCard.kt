package com.unison.roomapplication.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.unison.roomapplication.R

@Composable
fun PresentationCard(navController: NavController, modifier: Modifier = Modifier) {
    PresentationCardBackground(modifier = modifier) {
        PresentationCardHeader()

        PresentationCardMedia()

    }

}

//@Preview
//@Composable
//private fun PresentationCardPreview() {
//    PresentationCard()
//}



@Composable
fun PresentationCardBackground(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer)

    ) {


    }
    Text(text = "Hello", fontSize = 30.sp, modifier = Modifier.zIndex(-1f))
    content()
}

@Composable
fun PresentationCardHeader() {
    PresentationCardBody()

}


@Composable
fun PresentationCardBody(modifier: Modifier = Modifier) {
    Card{
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .height(IntrinsicSize.Min)
                .padding(10.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(200.dp)

            ){
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .align(Alignment.BottomCenter)
                        .background(Color.White, shape = CircleShape)
                )
                Image(
                    painterResource(id = R.drawable.yoformal),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .clip(CircleShape)
                )
            }

            Text(
                text = "Octavio Zenil Lopez",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "Full Stack Developer",
                fontSize = 16.sp,
                letterSpacing = -1.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }

}

@Composable
fun PresentationCardMedia(modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 16.dp)
            .fillMaxWidth()


    ) {
        Column(
            modifier = Modifier
                .width(intrinsicSize = IntrinsicSize.Min)
                .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(20.dp))

        ) {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(20.dp))
                    .padding(12.dp)
            ) {
                Column (
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(20.dp))
                ) {

                    Row (
                        modifier = Modifier
                            .width(250.dp)
                            .padding(
                                top =
                                12.dp
                            )
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mail),
                            contentDescription = "Mail",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .size(20.dp)

                        )
                        Text(
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontWeight = FontWeight.SemiBold

                            ,text = "octaviozenil@gmail.com")
                    }
                    Row (
                        modifier = Modifier
                            .width(250.dp)
                            .padding(
                                top =
                                12.dp
                            )
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.linkedin),
                            contentDescription = "Linkedin",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .size(20.dp)

                        )
                        Text(
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontWeight = FontWeight.SemiBold

                            ,text = "octaviozenil")
                    }
                    Row (
                        modifier = Modifier
                            .width(250.dp)
                            .padding(
                                top =
                                12.dp
                            )
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "Phone number",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .size(20.dp)

                        )
                        Text(
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontWeight = FontWeight.SemiBold

                            ,text = "+52 (662) 167 8480")
                    }

                }
            }
        }
    }
}