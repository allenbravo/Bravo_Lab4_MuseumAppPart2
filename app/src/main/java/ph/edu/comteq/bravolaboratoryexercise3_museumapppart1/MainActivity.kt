package ph.edu.comteq.bravolaboratoryexercise3_museumapppart1

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

// Custom Fonts
val playfairDisplay = FontFamily(
    Font(R.font.playfairdisplayregular, weight = FontWeight.Normal)
)

val optima = FontFamily(
    Font(R.font.optima1, weight = FontWeight.Normal)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BravoLaboratoryExercise3MuseumAppPart1Theme {
                Homepage()
            }
        }
    }
}

@Composable
fun Homepage(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Image(
            painter = painterResource(id = R.drawable.louvre),
            contentDescription = "Louvre Museum",
            modifier = Modifier
                .fillMaxWidth(0.9f)   // 90% width
                .align(Alignment.Center) // gitna siya sa screen
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Gallery Logo",
                modifier = Modifier.size(100.dp)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 60.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Experience Art",
                fontFamily = playfairDisplay,
                fontSize = 28.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(250.dp) // lapad ng title
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "We are thrilled to invite you to join us for an extraordinary event that will immerse you in the world of art.",
                fontFamily = optima,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(300.dp) // lapad ng paragraph
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val intent = Intent(context, ExploreActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD4AF37)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .width(220.dp) // lapad ng button
                    .padding(vertical = 8.dp)
            ) {
                Text("Explore Now", color = Color.Black, fontFamily = optima)
            }
        }
    }
}

@Composable
fun ExploreScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("Welcome to Explore Page", fontSize = 24.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    BravoLaboratoryExercise3MuseumAppPart1Theme {
        Homepage()
    }
}
