package ph.edu.comteq.bravolaboratoryexercise3_museumapppart1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import kotlinx.coroutines.delay
import ph.edu.comteq.bravolaboratoryexercise3_museumapppart1.ui.theme.BravoLaboratoryExercise3MuseumAppPart1Theme

val playfairdisplayregular = FontFamily(Font(R.font.playfairdisplayregular, FontWeight.Normal))
val optima = FontFamily(Font(R.font.optima1, FontWeight.Normal))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BravoLaboratoryExercise3MuseumAppPart1Theme {
                Scaffold { innerPadding ->
                    GalleryScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GalleryScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // SCREEN FADE-IN
    val screenAlpha = remember { Animatable(0f) }

    // IMAGE REVEAL (0f = not visible, 1f = fully visible)
    val imageReveal = remember { Animatable(0f) }

    // TITLE FADE-IN
    val titleAlpha = remember { Animatable(0f) }

    // TYPEWRITER TEXT
    var showText by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // Fade in entire screen
        screenAlpha.animateTo(1f, animationSpec = tween(800))

        // Museum image reveal (top to bottom)
        imageReveal.animateTo(1f, animationSpec = tween(3000))

        // Fade in title
        titleAlpha.animateTo(1f, animationSpec = tween(1500))

        // Start typewriter text after title
        showText = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .alpha(screenAlpha.value)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        // LOGO
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Gallery Logo",
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
                .padding(bottom = 32.dp)
        )

        // MAIN IMAGE WITH REVEAL
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(400.dp)
                .clip(RectangleShape)
        ) {
            val revealHeight = 400.dp * imageReveal.value
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(revealHeight)
                    .clip(RectangleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.louvre),
                    contentDescription = "Louvre Museum",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // EXPERIENCE ART TITLE
            Text(
                text = "Experience Art",
                color = Color.White.copy(alpha = titleAlpha.value),
                fontFamily = playfairdisplayregular,
                fontSize = 33.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-5).dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // TYPEWRITER INTRO TEXT
        if (showText) {
            TypewriterText(
                text = "We are thrilled to invite you to join us for an extraordinary event that will immerse you in the world of art.",
                charDelayMillis = 22,
                onFinished = { showButton = true },
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // EXPLORE BUTTON
        if (showButton) {
            Button(
                onClick = {
                    val intent = Intent(context, ExploreActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD4AF37),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Explore Now",
                    fontFamily = optima,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun TypewriterText(
    text: String,
    charDelayMillis: Long = 30,
    modifier: Modifier = Modifier,
    onFinished: (() -> Unit)? = null
) {
    var displayText by remember { mutableStateOf("") }
    LaunchedEffect(text) {
        for (i in text.indices) {
            displayText = text.substring(0, i + 1)
            delay(charDelayMillis)
        }
        onFinished?.invoke()
    }

    Text(
        text = displayText,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontFamily = optima,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GalleryScreenPreview() {
    BravoLaboratoryExercise3MuseumAppPart1Theme {
        GalleryScreen()
    }
}
