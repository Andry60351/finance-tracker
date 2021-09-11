package com.andrystudio.assetliabilitytracker.customViews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.andrystudio.assetliabilitytracker.ui.theme.AssetLiabilityTrackerTheme

@Composable
fun AL_CreateOutlinedTextField(
    string: MutableState<String>,
    label: String,
    focusManager: FocusManager,
    focusDirection: FocusDirection
) {
    OutlinedTextField(
        value = string.value,
        onValueChange = {
            string.value = it
        },
        label = {
            Text(
                text = label
            )
        },
        keyboardActions = KeyboardActions(onDone = {
            // if focusDirection gets passed as Previous, just clear the focus to hide the keyboard
            if (focusDirection == FocusDirection.Previous) {
                focusManager.clearFocus(true)
            } else {
                focusManager.moveFocus(focusDirection = focusDirection)
            }
        }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val stringMutable = remember {
        mutableStateOf("Demo")
    }
    AssetLiabilityTrackerTheme {
        AL_CreateOutlinedTextField(
            string = stringMutable,
            label = "Cost",
            focusManager = LocalFocusManager.current,
            focusDirection = FocusDirection.Previous
        )
    }
}