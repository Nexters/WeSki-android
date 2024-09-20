package com.dieski.weski.presentation.core.designsystem.snowflake

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.model.Snowflake
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 *
 * @author   JGeun
 * @created  2024/09/17
 */
@Composable
fun WindBlownSnowflakeEffect() {
	val configuration = LocalConfiguration.current
	val density = LocalDensity.current

	val screenWidthPx = with(density) {
		configuration.screenWidthDp * this.density
	}

	val screenHeightPx = with(density) {
		configuration.screenHeightDp * this.density
	}

	val snowflakes = remember { mutableStateListOf<Snowflake>() }

	var delaySum = 0L
	val amplitude = 6f
	LaunchedEffect(Unit) {
		while (true) {
			if (delaySum % 50L == 0L) {
				val x = Random.nextInt(screenWidthPx.toInt()).toFloat()
				val y = Random.nextInt(screenHeightPx.toInt() / 2).toFloat()
				val snowflake = Snowflake(
					offset = Offset(x, -y),
					angle = Random.nextFloat() * (PI / 2).toFloat() + (PI / 4).toFloat(),
					createTime = System.currentTimeMillis()
				)
				snowflakes.add(snowflake)
			}

			val changedSnowflakes = snowflakes.map {
				val angle = it.angle
				val horizontalOffset = amplitude * cos(angle)
				val verticalOffset = amplitude * sin(angle)

				val newX = it.offset.x + horizontalOffset
				val newY = it.offset.y + verticalOffset

				if (newX <= 0 || newY >= screenHeightPx) {
					val x = Random.nextInt(screenWidthPx.toInt()).toFloat()
					val y = Random.nextInt(screenHeightPx.toInt() / 2).toFloat()

					it.copy(offset = Offset(x, -y))
				} else {
					it.copy(offset = Offset(newX, newY))
				}
			}.filter {
				System.currentTimeMillis() - it.createTime <= 4000
			}

			snowflakes.clear()
			snowflakes.addAll(changedSnowflakes)

			delay(10L)
			delaySum = (delaySum + 10) % 100000000
		}
	}

	val yellow = Color(0xFFFdFF99)

	Box(
		modifier = Modifier
			.fillMaxSize()
	) {
		snowflakes.forEach { light ->
			Icon(
				modifier = Modifier
					.size(20.dp)
					.offset(
						x = with(density) { light.offset.x.toDp() },
						y = with(density) { light.offset.y.toDp() }
					)
					.alpha(0.4f),
				painter = painterResource(id = R.drawable.snow_texture),
				contentDescription = "snow_texture",
				tint = WeskiColor.White
			)
		}
	}
}