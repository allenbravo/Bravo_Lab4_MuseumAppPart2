package ph.edu.comteq.bravolaboratoryexercise3_museumapppart1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.bravolaboratoryexercise3_museumapppart1.ui.theme.BravoLaboratoryExercise3MuseumAppPart1Theme

class ArtistsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BravoLaboratoryExercise3MuseumAppPart1Theme {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Background image
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = "Background",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )


                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xCCFFFFFF),
                                        Color(0xFFFFFFFF)
                                    )
                                )
                            )
                    )

                    // Main content
                    Scaffold(
                        containerColor = Color.Transparent,
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        ArtistScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

// Colors
val Gold = Color(0xFFFFD700)

// Data classes
data class Artwork(val id: Int, val description: String)
data class ArtistData(
    val id: Int,
    val name: String,
    val years: String,
    val profileImage: Int,
    val artworks: List<Artwork>
)

// Sample data
val sampleArtists = listOf(
    ArtistData(
        id = 1,
        name = "Leonardo da Vinci",
        years = "1452 - 1519",
        profileImage = R.drawable.leonardo_da_vinci,
        artworks = listOf(
            Artwork(R.drawable.mona_lisa, "Mona Lisa"),
            Artwork(R.drawable.lady_ermine, "Lady with an Ermine"),
            Artwork(R.drawable.litta_madonna, "Litta Madonna")
        )
    ),
    ArtistData(
        id = 2,
        name = "Michelangelo",
        years = "1475 - 1564",
        profileImage = R.drawable.michelangelo,
        artworks = listOf(
            Artwork(R.drawable.david, "David"),
            Artwork(R.drawable.delphic_sibyl, "Delphic Sibyl"),
            Artwork(R.drawable.torment_of_saint_anthony, "Torment of Saint Anthony")
        )
    ),
    ArtistData(
        id = 3,
        name = "Gustav Klimt",
        years = "1862 - 1918",
        profileImage = R.drawable.gustav_klimt,
        artworks = listOf(
            Artwork(R.drawable.adele_bloch_bauer, "Adele Bloch-Bauer"),
            Artwork(R.drawable.lady_with_fan, "Lady with Fan"),
            Artwork(R.drawable.the_kiss, "The Kiss")
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistScreen(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Artists", "Artworks")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Explore the Art of",
            fontSize = 30.sp,
            color = Color.Black
        )
        Text(
            text = "Renaissance",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Gold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Box
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Type to search...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.scan),
                    contentDescription = "Scan Icon",
                    modifier = Modifier.size(24.dp)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs
        PrimaryTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = Gold,
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    height = 3.dp,
                    color = Gold
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                val selected = selectedTabIndex == index
                Tab(
                    selected = selected,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selected) Gold else Color.Gray,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Artist list
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(sampleArtists) { artist ->
                ArtistCard(artist)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ArtistCard(artist: ArtistData) {
    val context = LocalContext.current
    val isLeonardo = artist.name == "Leonardo da Vinci"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (isLeonardo) {
                    val intent = Intent(context, ExhibitActivity::class.java)
                    intent.putExtra("artistName", artist.name)
                    context.startActivity(intent)
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ArtistProfileImage(artist)
            Spacer(modifier = Modifier.width(16.dp))
            ArtistTextInfo(artist)
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(artist.artworks) { artwork ->
                Image(
                    painter = painterResource(id = artwork.id),
                    contentDescription = artwork.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                )
            }
        }
    }
}

@Composable
fun ArtistProfileImage(artist: ArtistData) {
    Image(
        painter = painterResource(id = artist.profileImage),
        contentDescription = artist.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.6f))
    )
}

@Composable
fun ArtistTextInfo(artist: ArtistData) {
    Column {
        Text(
            text = artist.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = artist.years,
            fontSize = 14.sp,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArtistPreview() {
    BravoLaboratoryExercise3MuseumAppPart1Theme {
        ArtistScreen()
    }
}