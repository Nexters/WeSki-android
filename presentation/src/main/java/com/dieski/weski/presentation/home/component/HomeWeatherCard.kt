package com.dieski.weski.presentation.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.util.debounceClickable
import com.dieski.weski.presentation.home.model.HomeWeatherUiModel

@Composable
internal fun HomeWeatherCard(
    weatherUiModel: HomeWeatherUiModel,
    onCardClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .debounceClickable { onCardClick() }
            .padding(vertical = 20.dp, horizontal = 12.dp)
    ) {
        Text(
            text = weatherUiModel.skiResortName
        )
    }
}