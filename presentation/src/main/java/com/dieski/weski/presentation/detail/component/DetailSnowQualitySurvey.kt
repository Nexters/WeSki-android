package com.dieski.weski.presentation.detail.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.button.cta.CtaButton
import com.dieski.weski.presentation.core.designsystem.button.vote.VoteButton
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.ui.theme.WeskiTheme

@Composable
internal fun DetailSnowQualitySurvey(
    modifier: Modifier = Modifier,
    onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> }
) {
    var selectedVoteBtnIndex by remember { mutableIntStateOf(0) }

   Column(
     modifier = modifier
         .fillMaxWidth()
         .background(WeskiColor.White)
         .padding(vertical = 32.dp, horizontal = 24.dp)
   ) {
       Text(
           text = "오늘의 설질",
           style = WeskiTheme.typography.title3SemiBold,
           color = WeskiColor.Gray80
       )
       
       Spacer(modifier = Modifier.height(24.dp))

       Text(
           text =  "상태가 좋아요",
           style = WeskiTheme.typography.title3SemiBold,
           color = WeskiColor.Gray80
       )

       Spacer(modifier = Modifier.height(4.dp))

       Text(
           text =  "34명 중 12명이 투표 했어요",
           style = WeskiTheme.typography.body2SemiBold,
           color = WeskiColor.Gray60
       )

       Spacer(modifier = Modifier.height(24.dp))

       Text(
           text = "오늘 같은 날씨는 설질 괜찮을까요?",
           style = WeskiTheme.typography.title3SemiBold,
           color = WeskiColor.Gray90
       )

       Spacer(modifier = Modifier.height(20.dp))
       
       VoteButton(
           text = "괜찮을 것 같아요",
           isSelected = selectedVoteBtnIndex == 0,
           onClick = {
               selectedVoteBtnIndex = 0
           }
       )

       Spacer(modifier = Modifier.height(12.dp))

       VoteButton(
           text = "별로일 것 같아요",
           isSelected = selectedVoteBtnIndex == 1,
           onClick = {
               selectedVoteBtnIndex = 1
           }
       )

       Spacer(modifier = Modifier.height(20.dp))

       CtaButton(
            text = "투표하기",
            onClick = {
                onShowSnackBar("투표가 완료되었어요", null)
            }
        )   
   }
}

@DevicePreviews
@ThemePreviews
@Composable
private fun DetailSnowQualitySurveyPreview() {
    DetailSnowQualitySurvey()
}