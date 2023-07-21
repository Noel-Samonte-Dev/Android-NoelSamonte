package noel.samonte.appdevtestproject.Views

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import noel.samonte.appdevtestproject.Data.Model.ButtonLogsModel
import noel.samonte.appdevtestproject.Data.ViewModel.ButtonLogsViewModel
import noel.samonte.appdevtestproject.R
import noel.samonte.appdevtestproject.ui.theme.AppDevTestProjectTheme

class ButtonEventLogs : ComponentActivity() {
    private lateinit var vm: ButtonLogsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDevTestProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    vm = ViewModelProvider(this)[ButtonLogsViewModel::class.java]
                    vm.readAllData.observe(this, androidx.lifecycle.Observer {
                        vm.getEvents(it)
                    })
                    body()
                }
            }
        }
    }

    @Composable
    fun body() {
        val activity = LocalContext.current as Activity
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp)
                    .clickable {
                        activity.finish()
                    },
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = "Button Event Logger",
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 0.dp),
                    color = colorResource(id = R.color.sky_blue),
                    fontSize = 18.sp
                )
            }

            val event_list = vm.event_list.collectAsState().value
            if (event_list.size > 0) {
                event_list.reverse()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState())
                        .background(Color.White)
                        .padding(top = 30.dp, bottom = 30.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    event_list.forEach {
                        Card(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                            elevation = 10.dp,
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(width = 1.dp, color = Color.Black)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Button ${it.button_id} pressed",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(5.dp)
                                )

                                Text(
                                    text = "@",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(5.dp)
                                )

                                Text(
                                    text = it.created_at,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No data found.",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}