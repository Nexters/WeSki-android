package com.dieski.weski.presentation.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.dieski.weski.presentation.R

/**
 *
 * @author   JGeun
 * @created  2024/08/06
 */
@Composable
fun LogoBar(
	onBackButtonShow: Boolean = false,
	onShareButtonShow: Boolean = false,
	share: () -> Unit,
	navigateUp:() -> Unit
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			painter = rememberAsyncImagePainter(model = R.drawable.ic_launcher_background),
			contentDescription = "back"
		)

		Text(
			text = "WeSki",
			fontSize = 20.sp
		)

		Icon(
			painter = rememberAsyncImagePainter(model = R.drawable.ic_launcher_background),
			contentDescription = "share"
		)
	}
}

@Preview
@Composable
internal fun LogoBarPreview() {
	LogoBar(
		onBackButtonShow = true,
		onShareButtonShow = true,
		share = {},
		navigateUp = {}
	)
}