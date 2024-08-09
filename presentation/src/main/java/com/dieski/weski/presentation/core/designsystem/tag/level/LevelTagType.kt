package com.dieski.weski.presentation.core.designsystem.tag.level

import androidx.compose.ui.graphics.Color
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor

enum class LevelTagType(
    val text: String,
    val bgColor: Color
) {
    BEGINNER(
        text = "초급",
        bgColor = WeskiColor.Sub02
    ),
    ELEMENTARY(
        text = "초중급",
        bgColor = WeskiColor.Sub02
    ),
    INTERMEDIATE(
        text = "중급",
        bgColor = WeskiColor.Sub04
    ),
    UPPER_INTERMEDIATE(
        text = "중상급",
        bgColor = WeskiColor.Sub04
    ),
    ADVANCED(
        text = "상급",
        bgColor = WeskiColor.Gray70
    ),
    UPPER_ADVANCED(
        text = "최상급",
        bgColor = WeskiColor.Gray70
    )
}