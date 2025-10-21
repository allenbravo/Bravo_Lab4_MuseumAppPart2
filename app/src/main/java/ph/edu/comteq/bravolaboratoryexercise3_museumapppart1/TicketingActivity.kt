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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    var generalAdmission by remember { mutableStateOf(0) }
    var freeTicket by remember { mutableStateOf(0) }
    val pricePerTicket = 500
    val total = generalAdmission * pricePerTicket
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
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            // TOP IMAGE
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.museum),
                    contentDescription = "Museum",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.7f))
                )
                Text(
                    text = "Official\nTicketing Service",
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 36.sp
                    )
                )
            }

            // 1. DATE TO VISIT
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "1. Date to Visit",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(fontSize = 20.sp, color = Color(0xFFd29f1b))
                )
                Divider(color = Color.White, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
            }

            // DatePicker
            DatePicker(
                state = datePickerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 350.dp, max = 450.dp)
                    .padding(horizontal = 16.dp),
                title = null,
                headline = null,
                showModeToggle = false,
                colors = DatePickerDefaults.colors(
                    containerColor = Color.Black,
                    dayContentColor = Color.White,
                    disabledDayContentColor = Color.Gray,
                    weekdayContentColor = Color(0xFFd29f1b),
                    subheadContentColor = Color.White,
                    yearContentColor = Color.White,
                    currentYearContentColor = Color(0xFFd29f1b),
                    selectedDayContentColor = Color.Black,
                    selectedDayContainerColor = Color(0xFFd29f1b),
                    selectedYearContentColor = Color.Black,
                    selectedYearContainerColor = Color(0xFFd29f1b)
                )
            )

            // 2. NUMBER OF TICKETS
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "2. Number of Tickets",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(fontSize = 20.sp, color = Color(0xFFd29f1b))
                )
                Divider(color = Color.White, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
            }

            // General Admission Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("General Admission", color = Color.White, fontSize = 18.sp)
                    Text("₱500", color = Color(0xFFd29f1b), fontSize = 18.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = CircleShape, color = Color.DarkGray) {
                        IconButton(onClick = { if (generalAdmission > 0) generalAdmission-- }) {
                            Text("-", color = Color.White, fontSize = 20.sp)
                        }
                    }
                    Text(
                        "$generalAdmission",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Surface(shape = CircleShape, color = Color.DarkGray) {
                        IconButton(onClick = { generalAdmission++ }) {
                            Text("+", color = Color.White, fontSize = 20.sp)
                        }
                    }
                }
            }

            // Free Ticket Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Under 18s, Under 26s\nResidents of the EEA,\n Museum Members,\n Professionals",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Text("FREE", color = Color(0xFFd29f1b), fontSize = 18.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = CircleShape, color = Color.DarkGray) {
                        IconButton(onClick = { if (freeTicket > 0) freeTicket-- }) {
                            Text("-", color = Color.White, fontSize = 20.sp)
                        }
                    }
                    Text(
                        "$freeTicket",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Surface(shape = CircleShape, color = Color.DarkGray) {
                        IconButton(onClick = { freeTicket++ }) {
                            Text("+", color = Color.White, fontSize = 20.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }

        // TOTAL + CHECKOUT
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color(0xFFd29f1b))
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Total: ₱$total", fontSize = 22.sp, color = Color.Black)
            Button(
                onClick = { /* TODO checkout function */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text("Checkout", fontSize = 18.sp, color = Color(0xFFd29f1b))
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
