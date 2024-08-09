package com.dieski.weski.presentation.core.designsystem.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
fun WeskiHeader(
    showBackButton: Boolean,
    showShareButton: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 11.dp, horizontal = 12.dp)
    ) {
        if (showBackButton) {
            Icon(
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.icn_chevron_left),
                tint = Color.Black,
                contentDescription = "뒤로가기"
            )
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "WeSki",
            textAlign = TextAlign.Center,
            style = WeskiTheme.typography.heading2Bold,
            color = WeskiColor.Gray90
        )

        if (showShareButton) {
            Icon(
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.icn_share),
                tint = WeskiColor.Gray90,
                contentDescription = "공유하기"
            )
        }

    }
}

@DevicePreviews
@ThemePreviews
@Composable
private fun HeaderPreview() {
    WeskiTheme {
        WeskiHeader(showBackButton = true, showShareButton = true)
    }
}