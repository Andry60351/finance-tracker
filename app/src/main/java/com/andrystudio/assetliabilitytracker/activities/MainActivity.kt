package com.andrystudio.assetliabilitytracker.activities

import android.content.res.Configuration.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andrystudio.assetliabilitytracker.Templates.AL_TopBar
import com.andrystudio.assetliabilitytracker.ui.theme.AssetLiabilityTrackerTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssetLiabilityTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AL_NavHost()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun AL_NavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "al_layout",
        builder = {

            composable(route = "al_layout") {
                OpenAnimation {
                    AL_Layout(navController = navController)
                }
            }
            composable(route = "al_create") {
                EnterAnimation {
                    AL_CreateLayout(navController = navController)
                }
            }
        }
    )
}

@Composable
fun AL_Layout(navController: NavController) {

    Scaffold(
        topBar = { AL_TopBar() },
        bottomBar = {}
    ) {
        Column {
            AL_LIST(navController = navController)
        }
    }
}


@Composable
fun AL_LIST(modifier: Modifier = Modifier, navController: NavController) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),

        content = {

            items(100) {
                ALCard(
                    name = "Real Estate",
                    profitAmount = 39.03f,
                    isAsset = true,
                    navController = navController

                )
                ALCard(
                    name = "Youtube Premium",
                    profitAmount = 23.03f,
                    isAsset = false,
                    navController = navController
                )
                ALCard(
                    name = "Spotify",
                    profitAmount = 19.40f,
                    isAsset = false,
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun ALCard(
    name: String,
    profitAmount: Float,
    isAsset: Boolean,
    navController: NavController
) {
    val color = if (isAsset) Color(0xFF1EE426) else Color(0xFFFF0056)
    var profitText = if (isAsset) "+ £${String.format("%.2f", profitAmount)}" else "- £${
        String.format(
            "%.2f",
            profitAmount
        )
    }"
    val context = LocalContext.current
    Card(
        elevation = 3.dp,
        modifier = Modifier
            .padding(10.dp)
            .height(60.dp)
            .clickable {
                navController.navigate("al_create") {
                    launchSingleTop = true
                }
            },
        shape = RoundedCornerShape(7.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
//            Spacer(modifier = Modifier.weight(0.4f))
            TypeIndicator(
                cornerRadius = 3.dp,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                color = color,

                )
            Text(
                text = name,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = profitText,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                ),
                modifier = Modifier.padding(end = 10.dp)
            )
//            Spacer(modifier = Modifier.weight(0.4f))
        }
    }
}

// this function will indicate what "type" the asset is
// type in the sense of "is it an asset? or a liability?"
// Green = asset
// Red = liability
@Composable
fun TypeIndicator(
    cornerRadius: Dp,
    modifier: Modifier = Modifier,
    color: Color
) {
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = modifier
                .size(20.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(color)
        ) {
        }
    }
}

@Composable
@ExperimentalAnimationApi
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(
//            expandFrom = Alignment.Start,
//            initialOffsetX = { -2000 },

        ),
//                + expandVertically(
//            expandFrom = Alignment.Top
//        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}
@Composable
@ExperimentalAnimationApi
fun OpenAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(
            initialAlpha = 0f,
//            expandFrom = Alignment.Start,
//            initialOffsetX = { -40 },
        ),
//                + (
//            expandFrom = Alignment.Top
//        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}
