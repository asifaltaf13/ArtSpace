package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {

    val painterResourceIds = arrayOf(
        R.drawable.one,
        R.drawable.two,
        R.drawable.three,
        R.drawable.four,
        R.drawable.five,
        R.drawable.six,
        R.drawable.seven,
    )

    val artistNames = arrayOf(
        "1 by Steve Johnson",
        "2 by Steve Johnson",
        "3 by Steve Johnson",
        "4 by Zaksheuskaya",
        "5 by Pixabay",
        "6 by Steve Johnson",
        "7 by Steve Johnson"
    )

    var state by remember{ mutableStateOf( 0) }
    val resId = painterResourceIds[state] // subtract to get correct index
    val name = artistNames[state]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        ArtworkWall(resId)

        ArtworkDescriptor(name)

        DisplayController(
            nextAction = { state = if(state == 6) 0 else state+1 },
            previousAction = { state = if(state == 0) 6 else state-1 }
        )
    }
}

@Composable
fun ArtworkWall(resId: Int) {
    Surface(
        elevation = 16.dp,
        border = BorderStroke(4.dp, Color.Gray),
        shape = RoundedCornerShape(4.dp)
    ) {
        Image(
            painter = painterResource(resId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .fillMaxHeight(0.50f)
                .padding(32.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ArtworkDescriptor(name: String) {
    Surface (
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .width(320.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.artist_name, name),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(id = R.string.source_name, "Pexels"),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun DisplayController(nextAction: () -> Unit, previousAction: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = previousAction,
            modifier = Modifier.defaultMinSize(minWidth = 152.dp)
        ) {
            Text(text = "Previous")
        }

        Button(
            onClick = nextAction,
            modifier = Modifier.defaultMinSize(minWidth = 152.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}