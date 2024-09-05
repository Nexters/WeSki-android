package com.dieski.weski.presentation.core.designsystem.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.dieski.weski.presentation.core.util.noRippleClickable
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
fun WeskiHeader(
    modifier: Modifier = Modifier,
    showBackButton: Boolean,
    showShareButton: Boolean,
    bgColor: Color = Color.Transparent,
    onClickBackButton: () -> Unit = {},
    onShare: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(bgColor)
            .padding(vertical = 14.dp, horizontal = 12.dp)
    ) {
        if (showBackButton) {
            Icon(
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.CenterStart)
                    .noRippleClickable { onClickBackButton() },
                painter = painterResource(id = R.drawable.ic_arrow_back),
                tint = Color.Black,
                contentDescription = "뒤로가기"
            )
        }

        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .width(67.dp)
                .height(21.dp),
            painter = painterResource(id = R.drawable.ic_weski_header),
            contentDescription = "WeSki Logo"
        )

        if (showShareButton) {
            Icon(
                modifier = Modifier
                    .size(26.dp)
                    .align(Alignment.CenterEnd)
                    .noRippleClickable { onShare() },
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