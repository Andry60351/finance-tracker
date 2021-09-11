package com.andrystudio.assetliabilitytracker.Templates

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.andrystudio.assetliabilitytracker.ui.theme.AssetLiabilityTrackerTheme


@Composable
fun AL_TopBar() {
    TopAppBar(
        title = { Text(text = "Assets & Liabilities Tracker") },

    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AL_BarPreviewDark() {
    AssetLiabilityTrackerTheme() {
        AL_TopBar()
    }
}

@Preview(showBackground = true)
@Composable
fun DAL_BarPreviewLight() {
    AssetLiabilityTrackerTheme() {
        AL_TopBar()
    }
}