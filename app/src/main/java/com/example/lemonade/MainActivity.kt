package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.TypedArrayUtils.getText
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.*
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage()
                }
            }
        }
    }
}

@Composable
fun HomePage() {

    var currentAction by remember { mutableStateOf(1) }
    var randomValue by remember { mutableStateOf(Random.nextInt(2, 6))}

    Text(
        text = "Lemonade",
        modifier = Modifier
            .height(70.dp)
            .background(color = Color.Cyan)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .fillMaxWidth(),
        color = Color.Black,
        fontSize = 30.sp,
        textAlign = TextAlign.Center)

    var nextImage = R.drawable.lemon_tree
    var nextAction = R.string.first_action

    when (currentAction){
        1 -> {
            nextImage = R.drawable.lemon_tree
            nextAction = R.string.first_action
        }
        in 2..randomValue  + 1-> {
            nextImage = R.drawable.lemon_squeeze
            nextAction = R.string.second_action
        }
        randomValue + 1 -> {
            nextImage = R.drawable.lemon_drink
            nextAction = R.string.third_action
        }
        randomValue + 2 -> {
            nextImage = R.drawable.lemon_restart
            nextAction = R.string.fourth_action
        }
        randomValue + 3 -> {
            currentAction = 1
            randomValue = Random.nextInt(2, 5)
        }
        else -> {}
    }


    Spacer(modifier = Modifier.height(420.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Image(
            painter = painterResource(id = nextImage),
            contentDescription = stringResource(nextAction),
            alignment = Alignment.Center,
            modifier = Modifier
                .background(color = Color.Cyan)
                .border(5.dp, Color.Gray)
                .clickable {
                    currentAction++
                }

        )

        Spacer(modifier = Modifier.height(420.dp))
        Text(
            text = stringResource(nextAction),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Transparent)
                .wrapContentHeight(align = Alignment.Bottom)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
        )
    }

}

@Composable
fun Spacer(modifier: Any) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        HomePage()
    }
}