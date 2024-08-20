package com.dieski.weski.presentation.detail.webcam

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.dieski.domain.model.WebMobileData
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.detail.DetailContract
import com.dieski.weski.presentation.detail.component.DetailSnowQualitySurvey
import com.dieski.weski.presentation.detail.component.WeskiWebView

@Composable
internal fun WebcamScreen(
    modifier: Modifier = Modifier,
    state: DetailContract.State = DetailContract.State(),
    onAction: (DetailContract.Event) -> Unit = {},
    isCurrentPage: Boolean = false,
    isWebViewActive: Boolean = true,
    onShowSnackBar: (message: String, action: String?) -> Unit = { _, _ -> }
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
        ) {
            if (!isWebViewActive) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.img_temp_webcam_map),
                    contentDescription = "웹캠 지도 이미지",
                    contentScale = ContentScale.FillWidth
                )
            } else {
                WeskiWebView(
                    webViewUrl = "${WebMobileData.WEB_MOBILE_URL}${WebMobileData.WEBCAM_PARAM}/${state.resortWebKey}",
                    startRenderingNow = isCurrentPage
                )
            }

            DetailSnowQualitySurvey(
                totalNum = state.snowMakingSurveyResult.totalNum,
                likeNum = state.snowMakingSurveyResult.likeNum,
                onSubmit = { isGood ->
                    onAction(DetailContract.Event.SubmitSnowQualitySurvey(state.resortId, isGood))
                },
                onShowSnackBar = onShowSnackBar
            )
        }
    }
}