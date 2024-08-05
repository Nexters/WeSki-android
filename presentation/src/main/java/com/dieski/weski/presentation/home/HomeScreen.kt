package com.dieski.weski.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun HomeRouter(
    padding: PaddingValues,
    onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
    HomeScreen()
}

@Composable
private fun HomeScreen(

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Home")
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}