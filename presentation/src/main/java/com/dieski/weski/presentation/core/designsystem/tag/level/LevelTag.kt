package com.dieski.weski.presentation.core.designsystem.tag.level

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
fun LevelTag(
    tagType: LevelTagType,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .widthIn(min = 56.dp)
            .background(color = tagType.bgColor, shape = RoundedCornerShape(6.dp))
            .padding(vertical = 2.5.dp, horizontal = 10.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = tagType.text,
            style = WeskiTheme.typography.body1SemiBold,
            color = WeskiColor.Gray10
        )
    }
}

@ThemePreviews
@Composable
private fun LevelTagPreview() {
    Column {
        LevelTag(tagType = LevelTagType.BEGINNER)
        LevelTag(tagType = LevelTagType.ELEMENTARY)
        LevelTag(tagType = LevelTagType.INTERMEDIATE)
        LevelTag(tagType = LevelTagType.UPPER_INTERMEDIATE)
        LevelTag(tagType = LevelTagType.ADVANCED)
    }
}