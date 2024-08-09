package com.dieski.weski.presentation.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun DetailSnowQualitySurvey(
    modifier: Modifier = Modifier
) {
   Column(
     modifier = modifier.fillMaxSize()
   ) {
       Text(text = "오늘의 설질")
       Text(text = "상태가 좋아요")
       Text(text = "34명 중 12명이 투표 했어요")

       Text(text = "오늘 같은 날씨는 설질 괜찮을까요?")
   }
}