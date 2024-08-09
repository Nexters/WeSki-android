package com.dieski.weski.presentation.detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.util.debounceClickable

@Composable
fun DetailWeatherCard(
    skiResortName: String
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .debounceClickable { }
            .padding(vertical = 20.dp, horizontal = 12.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = skiResortName
        )
    }
}