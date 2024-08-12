package com.dieski.weski.presentation.core.designsystem.button.scroll

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews

/**
 *
 * @author   JGeun
 * @created  2024/08/10
 */
@Composable
fun ScrollFloatButton(
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	onClick: () -> Unit = {},
	color: Color = WeskiColor.White,
	content: @Composable () -> Unit,
) {
	Surface(
		onClick = onClick,
		enabled = enabled,
		modifier = modifier,
		color = color,
		shape = CircleShape,
		shadowElevation = 2.dp,
		content = content
	)

}

@DevicePreviews
@ThemePreviews
@Composable
private fun ScrollFloatButtonPreview() {
	ScrollFloatButton() {
		Icon(
			modifier = Modifier.padding(12.dp).size(18.dp),
			painter = painterResource(id = R.drawable.ic_arrow_up),
			contentDescription = "위로 가기",
			tint = WeskiColor.Gray60
		)
	}
}