package com.dieski.weski.presentation.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun WeatherRouter(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    WeatherScreen()
}

@Composable
private fun WeatherScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "WeatherScreen")
    }
}

@Preview
@Composable
private fun WeatherScreenPreview() {
    WeatherScreen()
}