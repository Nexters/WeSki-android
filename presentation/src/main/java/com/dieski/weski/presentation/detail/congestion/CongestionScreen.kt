package com.dieski.weski.presentation.detail.congestion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.detail.component.WeskiWebView
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
internal fun CongestionScreen(
    modifier: Modifier = Modifier,
    isCurrentPage: Boolean = false,
    isWebViewActive: Boolean = true,
    onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> }
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(WeskiColor.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "인기 시간대",
                            style = WeskiTheme.typography.title3SemiBold,
                            color = WeskiColor.Gray90
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.icn_information_circle),
                            contentDescription = "정보",
                            tint = WeskiColor.Gray40
                        )
                    }

                    Text(
                        text = "TMAP · 08월 14일 업데이트 ",
                        style = WeskiTheme.typography.body1Medium,
                        color = WeskiColor.Gray50
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.img_temp_congestion),
                        contentDescription = "혼잡도 임시 이미지",
                        contentScale = ContentScale.FillWidth
                    )

                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "인기 시간대는 개장일 이후부터 확인할 수 있어요",
                        style = WeskiTheme.typography.title3SemiBold,
                        color = WeskiColor.Main01
                    )
                }
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(WeskiColor.Gray20)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "슬로프 운행 현황",
                            style = WeskiTheme.typography.title3SemiBold,
                            color = WeskiColor.Gray90
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.icn_information_circle),
                            contentDescription = "정보",
                            tint = WeskiColor.Gray40
                        )
                    }

                    Text(
                        text = "08월 14일 23:00 업데이트",
                        style = WeskiTheme.typography.body1Medium,
                        color = WeskiColor.Gray50
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))

                if (!isWebViewActive) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.img_temp_slope),
                        contentDescription = "슬로프 임시 이미지",
                        contentScale = ContentScale.FillWidth
                    )
                } else {
                    WeskiWebView(
                        webViewUrl = "https://we-ski-web.vercel.app/mobile/slop-status",
                        startRenderingNow = isCurrentPage
                    )
                }
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(WeskiColor.Gray20)
            )

            DetailSnowQualitySurvey(
                onShowSnackBar = onShowSnackBar
            )
        }
    }
}

@DevicePreviews
@ThemePreviews
@Composable
private fun CongestionScreenPreview() {
    WeskiTheme {
        CongestionScreen(isCurrentPage = true)
    }
}