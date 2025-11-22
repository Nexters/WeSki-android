package com.dieski.weski.presentation.core.common

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

/**
 * @author   JGeun
 * @created  2025/11/22
 */
@OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun ExoPlayerView(
	videoUri: String,
	modifier: Modifier = Modifier
) {
	val context = LocalContext.current
	// Initialize ExoPlayer
	val exoPlayer = remember { ExoPlayer.Builder(context).build() }


	// MediaSource를 생성하기 위한 DataSource.Factory 인스턴스 준비
	val dataSourceFactory = DefaultDataSource.Factory(context)
	// Create a MediaSource
	val mediaSource = remember(videoUri) {
		if (videoUri.endsWith("m3u8")) {
			HlsMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(videoUri))
		} else {
			ProgressiveMediaSource.Factory(dataSourceFactory)
				.createMediaSource(
					MediaItem.fromUri(Uri.parse(videoUri))
				)
		}
	}

	// Uri 변경 시마다 Exoplayer 셋팅
	LaunchedEffect(mediaSource) {
		exoPlayer.setMediaSource(mediaSource)
		exoPlayer.prepare()
		exoPlayer.playWhenReady = true // 자동 재생
	}

	// 생명주기 종료 시 Exoplayer 메모리 해제
	DisposableEffect(Unit) {
		onDispose {
			exoPlayer.release()
		}
	}

	// Exoplayer with AndroidView
	AndroidView(
		factory = { ctx ->
			PlayerView(ctx).apply {
				player = exoPlayer
				resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
				useController = false
			}
		},
		modifier = modifier
	)
}