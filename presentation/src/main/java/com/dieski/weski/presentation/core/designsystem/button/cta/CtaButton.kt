package com.dieski.weski.presentation.core.designsystem.button.cta

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.core.util.debounceClickable
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
fun CtaButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerPadding: PaddingValues = PaddingValues(vertical = 14.5.dp, horizontal = 24.dp)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = WeskiColor.Main01, shape = RoundedCornerShape(8.dp))
            .debounceClickable { onClick() }
            .padding(containerPadding)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            style = WeskiTheme.typography.title3SemiBold,
            textAlign = TextAlign.Center,
            color = WeskiColor.Gray10
        )
    }
}

@ThemePreviews
@DevicePreviews
@Composable
private fun CtaButtonPreview() {
    CtaButton(
        text = "CTA 버튼",
        onClick = {}
    )
}