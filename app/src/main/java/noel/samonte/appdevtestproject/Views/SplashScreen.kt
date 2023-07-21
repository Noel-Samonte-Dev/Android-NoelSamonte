package noel.samonte.appdevtestproject.Views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import noel.samonte.appdevtestproject.R
import noel.samonte.appdevtestproject.ui.theme.AppDevTestProjectTheme

class SplashScreen : ComponentActivity() {
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDevTestProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    body()
                }
            }
        }
    }

    @Composable
    fun body() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.noel_logo),
                contentDescription = null,
                modifier = Modifier
                    .width(width = 230.dp)
                    .height(height = 230.dp),
                contentScale = ContentScale.Crop
            )

            var timer_done by remember { mutableStateOf(false) }
            val context = LocalContext.current

            if (timer_done) {
                ButtonPageIntent(context = context)
            }

            LaunchedEffect(Unit) {
                delay(2000)
                timer_done = true
            }
        }
    }

    @Composable
    fun ButtonPageIntent(context: Context) {
        context.startActivity(Intent(context, ButtonPage::class.java))
        val intent = LocalContext.current as Activity
        intent.finish()
    }
}
