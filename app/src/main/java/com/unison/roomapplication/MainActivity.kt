package com.unison.roomapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.unison.roomapplication.navigation.NavManager
import com.unison.roomapplication.snackbar.SnackbarManager
import com.unison.roomapplication.ui.theme.RoomApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            RoomApplicationTheme {
                Surface {
                    App()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(modifier: Modifier = Modifier) {
    val snackbarManager = SnackbarManager(rememberCoroutineScope(), remember { SnackbarHostState() })

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarManager.snackbarHostState) }
        ) {
            NavManager(snackbarManager = snackbarManager)
        }
}


