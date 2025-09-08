package com.dieski.weski.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.noRippleClickable
import com.dieski.weski.presentation.ui.theme.WeskiTheme

/**
 *
 * @author   JGeun
 * @created  2025/09/08
 */
@Composable
internal fun ForceUpdateDialog(
	openPlayStoreUrl: () -> Unit,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier.fillMaxSize()
			.background(WeskiColor.Gray100.copy(alpha = 0.5f))
	) {
		Column(
			modifier = Modifier.widthIn(max = 316.dp)
				.background(color = WeskiColor.White, shape = RoundedCornerShape(20.dp))
				.align(Alignment.Center)
				.padding(top = 38.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
		) {
			Text(
				text = "최신 버전의 앱이 있어요!",
				style = WeskiTheme.typography.title2SemiBold,
				color = WeskiColor.Gray100,
				textAlign = TextAlign.Center,
				modifier = Modifier.fillMaxWidth()
					.padding(horizontal = 26.dp)
			)

			Spacer(Modifier.height(10.dp))

			Text(
				text = "안정적인 서비스 사용을 위해 최신 버전으로 업데이트 해주세요.",
				style = WeskiTheme.typography.body1Regular,
				color = WeskiColor.Gray60,
				textAlign = TextAlign.Center,
				modifier = Modifier.fillMaxWidth()
					.padding(horizontal = 26.dp)
			)

			Spacer(Modifier.height(18.dp))

			Text(
				modifier = Modifier.fillMaxWidth()
					.background(shape = RoundedCornerShape(8.dp), color = WeskiColor.Main01)
					.noRippleClickable { openPlayStoreUrl() }
					.padding(vertical = 16.5.dp),
				text = "플레이스토어로 이동하기",
				style = WeskiTheme.typography.title3SemiBold,
				color = WeskiColor.White,
				textAlign = TextAlign.Center
			)
		}
	}
}