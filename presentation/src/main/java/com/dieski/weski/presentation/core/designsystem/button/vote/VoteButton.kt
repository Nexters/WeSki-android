package com.dieski.weski.presentation.core.designsystem.button.vote

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
fun VoteButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    defaultBorderColor: Color =  WeskiColor.Gray30,
    selectedBorderColor: Color =  WeskiColor.Main01,
    selectedIconColor: Color =  WeskiColor.Main01,
    cornerDp: Dp = 8.dp
) {
    val borderColor = if(isSelected) selectedBorderColor else defaultBorderColor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerDp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(cornerDp))
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = WeskiTheme.typography.body1Regular,
            color = WeskiColor.Gray60
        )

        Box(
            modifier = Modifier.size(20.dp)
        ) {
            if (isSelected) {
                Icon(
                    painter = painterResource(id = R.drawable.icn_check),
                    contentDescription = "선택됨",
                    tint = selectedIconColor
                )
            }
        }
    }
}

@DevicePreviews
@ThemePreviews
@Composable
private fun  VoteButtonPreview() {

    Column {
        VoteButton(
            text = "투표 버튼",
            isSelected = false,
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )

        VoteButton(
            text = "투표 버튼",
            isSelected = true,
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}