package com.andrystudio.assetliabilitytracker.activities

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andrystudio.assetliabilitytracker.Templates.AL_TopBar
import com.andrystudio.assetliabilitytracker.customViews.AL_CreateOutlinedTextField
import com.andrystudio.assetliabilitytracker.ui.theme.AssetLiabilityTrackerTheme

class CreateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssetLiabilityTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    AL_CreateLayout()
                    AL_NavHost()
                }
            }
        }
    }
}

@Composable
fun AL_CreateLayout(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val AL_name = remember { mutableStateOf("") }
    val AL_cost = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            AL_TopBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            AL_CreateOutlinedTextField(
                string = AL_name,
                label = "Asset or Liability name",
                focusManager = focusManager,
                focusDirection = FocusDirection.Down
            )
            Text(text = "Change this with a radio button or something")
            AL_CreateOutlinedTextField(
                string = AL_cost,
                label = "Cost",
                focusManager = focusManager,
                focusDirection = FocusDirection.Previous
            )
        }
    }
}


//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun DarkCreateLayoutPreview() {
//    AssetLiabilityTrackerTheme() {
//        AL_CreateLayout()
//    }
//}
//
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
//@Composable
//fun LightCreateLayoutPreview() {
//    AssetLiabilityTrackerTheme() {
//        AL_CreateLayout()
//    }
//}
//
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
//@Composable
//fun thirdPreview() {
//    AssetLiabilityTrackerTheme() {
//        AL_CreateLayout()
//    }
//}
