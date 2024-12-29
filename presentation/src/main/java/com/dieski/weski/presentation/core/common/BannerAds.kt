package com.dieski.weski.presentation.core.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/**
 *
 * @author   JGeun
 * @created  2024/12/29
 */
@Composable
fun BannerAds(
	modifier: Modifier = Modifier,
	bannerAdUnitId: String
) {
	AndroidView(
		modifier = modifier.fillMaxWidth(),
		factory = { context ->
			AdView(context).apply {
				setAdSize(AdSize.BANNER)
				adUnitId = bannerAdUnitId
				loadAd(AdRequest.Builder().build())
			}
		},
		update = { adView ->
			adView.loadAd(AdRequest.Builder().build())
		}
	)
}