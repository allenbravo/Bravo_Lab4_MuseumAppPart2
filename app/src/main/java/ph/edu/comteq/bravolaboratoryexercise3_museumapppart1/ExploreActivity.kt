package ph.edu.comteq.bravolaboratoryexercise3_museumapppart1

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.bravolaboratoryexercise3_museumapppart1.ui.theme.BravoLaboratoryExercise3MuseumAppPart1Theme

class ExploreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BravoLaboratoryExercise3MuseumAppPart1Theme {
                ExplorePage()
            }
        }
    }
}

@Composable
fun ExplorePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Explore",
            fontFamily = playfairDisplay,
            fontSize = 32.sp,
            color = Color(0xFFD4AF37)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Upcoming Event",
                fontFamily = optima,
                fontSize = 18.sp,
                color = Color.White
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Tickets", color = Color.White, fontFamily = optima)
                Icon(
                    painter = painterResource(id = R.drawable.chevron_right),
                    contentDescription = "Go",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.renaissance),
                    contentDescription = "Renaissance Exhibition",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(200.dp)
                )

                Column(
                    modifier = Modifier
                        .background(Color.DarkGray)
                        .padding(16.dp)
                ) {
                    Text("Renaissance Exhibition", fontFamily = playfairDisplay, fontSize = 20.sp, color = Color.White)
                    Text("9:00 AM - 6:00 PM", color = Color.White, fontFamily = optima)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Indulge in the rich tapestry of Renaissance art",
                        color = Color(0xFFD4AF37),
                        fontFamily = optima
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("+33 (0)1 23 45 67 89", color = Color.White, fontFamily = optima)
                }

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Visit Gallery", color = Color.Black, fontFamily = optima)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExplorePagePreview() {
    BravoLaboratoryExercise3MuseumAppPart1Theme {
        ExplorePage()
    }
}