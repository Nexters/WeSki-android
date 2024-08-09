package com.dieski.weski.presentation.core.designsystem.button.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.debounceNoRippleClickable
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .debounceNoRippleClickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = WeskiTheme.typography.title3SemiBold,
            color = if (isSelected) WeskiColor.Gray90 else WeskiColor.Gray40
        )
    }
}

@DevicePreviews
@ThemePreviews
@Composable
private fun TabButtonPreview() {
    Column {
        TabButton(
            text = "탭 버튼",
            isSelected = true,
            onClick = {}
        )

        TabButton(
            text = "탭 버튼",
            isSelected = false,
            onClick = {}
        )
    }
}