package ph.edu.comteq.bravolaboratoryexercise3_museumapppart1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ExhibitActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val artistName = intent.getStringExtra("artistName") ?: "Unknown Artist"

        setContent {
            Scaffold { innerPadding ->
                ExhibitScreen(
                    artistName = artistName,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun ExhibitScreen(artistName: String, modifier: Modifier = Modifier) {

    val artworkTitle = "Lady with an Ermine"
    val artworkTimePlace = "c. 1489–91, Milan, Italy"
    val artworkComment = "It is a captivating image of exquisite elegance and reveals the artistic genius of Leonardo's incomparable creative mind."

    // UI layout
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C)) // dark background
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(Color(0xFF1C1C1C))
            ) {
                // Artwork image
                Image(
                    painter = painterResource(id = R.drawable.lady_ermine),
                    contentDescription = artworkTitle,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp))
                )

                // Artwork info section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFD4AF37))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = artworkTitle,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = artworkTimePlace,
                            fontSize = 14.sp,
                            color = Color(0xFF4B4B4B)
                        )
                    }
                }

                // Comment section with quote.png
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.quote),
                        contentDescription = "Quote Icon",
                        modifier = Modifier
                            .size(32.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = artworkComment,
                        fontSize = 14.sp,
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
