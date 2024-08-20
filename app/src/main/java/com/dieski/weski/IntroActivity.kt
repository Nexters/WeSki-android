package com.dieski.weski

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R

@Composable
fun IntroScreen() {
	Box(
		modifier = Modifier.fillMaxSize(),
	) {
		Image(
			modifier = Modifier.fillMaxSize(),
			painter = painterResource(id = R.drawable.img_splash_background),
			contentDescription = "background",
			contentScale = ContentScale.FillBounds
		)

		Box(
			modifier = Modifier.fillMaxWidth()
				.padding(horizontal = 79.dp)
				.align(Alignment.Center)
		) {
			Image(
				modifier = Modifier.align(Alignment.Center),
				painter = painterResource(id = R.drawable.img_logo),
				contentDescription = "background"
			)
		}
	}
}