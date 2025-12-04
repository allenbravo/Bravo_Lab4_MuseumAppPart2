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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

data class ArtworkData(
    val title: String,
    val years: String,
    val bornAt: String,
    val comment: String,
    val imageResId: Int
)

val artworksList = listOf(
    ArtworkData(
        "Mona Lisa",
        "c. 1503-19",
        "Florence, Italy",
        "The best known, the most visited, the most written about, the most sung about, the most parodied work of art in the world.",
        R.drawable.mona_lisa
    ),
    ArtworkData(
        "Litta Madonna",
        "c. 1490",
        "Italy",
        "This portrayal reflects the religious devotion of the Renaissance period and the virtues of motherhood.",
        R.drawable.litta_madonna
    ),
    ArtworkData(
        "Lady with an Ermine",
        "c. 1489–91",
        "Milan, Italy",
        "It is a captivating image of exquisite elegance and reveals the artistic genius of Leonardo's creative mind.",
        R.drawable.lady_ermine
    )
)

class ExhibitActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExhibitScreen()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ExhibitScreen() {

    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C)),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(
                count = artworksList.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
            ) { page ->

                val artwork = artworksList[page]

                
                val pageOffset = (pagerState.currentPage - page + pagerState.currentPageOffset).absoluteValue

                val rotation = pageOffset * 60f
                val scale = 1f - (pageOffset * 0.2f)
                val alpha = 1f - (pageOffset * 0.5f)

                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    modifier = Modifier
                        .graphicsLayer {
                            rotationY = rotation
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                            cameraDistance = 12f * density
                        }
                        .fillMaxWidth()
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.background(Color(0xFF1C1C1C))
                    ) {

                        Image(
                            painter = painterResource(artwork.imageResId),
                            contentDescription = artwork.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFD4AF37))
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(
                                    artwork.title,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    "${artwork.years}, ${artwork.bornAt}",
                                    fontSize = 14.sp,
                                    color = Color(0xFF4B4B4B)
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.quote),
                                contentDescription = null,
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                artwork.comment,
                                fontSize = 14.sp,
                                color = Color.LightGray
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
