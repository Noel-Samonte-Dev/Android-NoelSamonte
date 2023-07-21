package noel.samonte.appdevtestproject.Views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import noel.samonte.appdevtestproject.Data.Model.ButtonLogsModel
import noel.samonte.appdevtestproject.Data.ViewModel.ButtonLogsViewModel
import noel.samonte.appdevtestproject.R
import noel.samonte.appdevtestproject.ui.theme.AppDevTestProjectTheme
import java.text.SimpleDateFormat
import java.util.*

class ButtonPage : ComponentActivity() {
    private lateinit var vm: ButtonLogsViewModel

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
                    vm = ViewModelProvider(this)[ButtonLogsViewModel::class.java]
                    getEventLogs()
                    ButtonPageBody()
                }
            }
        }
    }

    @Composable
    fun ButtonPageBody() {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Button Event Logger",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(10.dp),
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                for (i in 1 until 5) {
                    Button(
                        onClick = {
                            addEventLog(id = i.toString())
                        },
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Gray
                        ),
                        elevation = ButtonDefaults.elevation(10.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .wrapContentSize(),
                            backgroundColor = Color.Gray
                        ) {
                            Text(
                                text = "Button $i",
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
                                color = colorResource(R.color.white),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = {
                        ButtonPageIntent()
                    },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.sky_blue)
                    ),
                    elevation = ButtonDefaults.elevation(10.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize(),
                        backgroundColor = colorResource(id = R.color.sky_blue)
                    ) {
                        Text(
                            text = "Show Logs",
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
                            color = colorResource(R.color.white),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    fun addEventLog(id: String){
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("MM/dd/yyyy h:mm aa")
        val date = formatter.format(time)

        val event = ButtonLogsModel(id, date,  0)
        vm.addEventLog(event)
        ButtonPageIntent()
        Toast.makeText(this, "Successfully Logged Event!", Toast.LENGTH_SHORT).show()
    }

    fun ButtonPageIntent() {
        getEventLogs()
        val intent = Intent(this, ButtonEventLogs::class.java)
        startActivity(intent)
    }

    fun getEventLogs() {
        vm.readAllData.observe(this, androidx.lifecycle.Observer {
            vm.getEvents(it)
        })
    }
}
