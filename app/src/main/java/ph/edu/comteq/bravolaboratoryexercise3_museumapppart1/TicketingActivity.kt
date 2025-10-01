@file:OptIn(ExperimentalMaterial3Api::class)

package ph.edu.comteq.bravolaboratoryexercise3_museumapppart1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.bravolaboratoryexercise3_museumapppart1.ui.theme.BravoLaboratoryExercise3MuseumAppPart1Theme
import java.util.Calendar

class TicketingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BravoLaboratoryExercise3MuseumAppPart1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Ticketing(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Ticketing(modifier: Modifier = Modifier) {

    val now = System.currentTimeMillis()
    val oneDayMillis = 24 * 60 * 60 * 1000L
    val initialDateMillis = now + (2 * oneDayMillis)
    val minSelectableDateMillis = now + (1 * oneDayMillis)

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMillis,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= minSelectableDateMillis
            }
        }
    )

    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.museum),
                    contentDescription = "Museum",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .background(Color.Black.copy(alpha = 0.7f))
                )
                Text(
                    text = "Official\nTicketing Service",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = playfairdisplayregular,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 36.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                DatePicker(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    state = datePickerState,
                    title = null,
                    showModeToggle = false,
                    headline = {
                        Text(
                            "1. Date to visit"
                        )
                    }
                )
            }
        }

        // Bottom row for total and checkout button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFFd29f1b))
                .padding(horizontal = 20.dp, vertical = 10.dp), // Added vertical padding para hindi dikit
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total: P500",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = playfairdisplayregular,
                    color = Color.Black,
                )
            )
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Text(
                    text = "Checkout",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = playfairdisplayregular,
                        color = Color(0xFFd29f1b)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketingPreview() {
    BravoLaboratoryExercise3MuseumAppPart1Theme {
        Ticketing()
    }
}
