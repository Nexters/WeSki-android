package com.dieski.weski.presentation.detail.congestion.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dieski.weski.presentation.core.designsystem.token.WeskiColor
import com.dieski.weski.presentation.core.util.DevicePreviews
import com.dieski.weski.presentation.core.util.ThemePreviews
import com.dieski.weski.presentation.detail.congestion.model.CongestionData
import com.dieski.weski.presentation.detail.congestion.model.CongestionLevel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 *
 * @author   JGeun
 * @created  2024/08/17
 */

/**
 * 데이터는 최대 16개까지 들어옴 -> 16개를 기준으로 잘라야함.
 */
@Composable
fun CongestionGraph(
	modifier: Modifier = Modifier,
	congestionDataList: ImmutableList<CongestionData>,
	congestionGraphBarWidth: Dp = 72.dp
) {
	val congestionBgBarHeightMap = remember { mutableMapOf<Int, Dp>() }
	val density = LocalDensity.current
	var showGraphBar by remember { mutableStateOf(false) }
	var congestionTagOffsetX by remember { mutableStateOf(0.dp) }

	Column {
		if (congestionTagOffsetX != 0.dp) {
			Spacer(Modifier.width(10.dp).height(10.dp).background(Color.Red))
			Spacer(modifier = Modifier.height(4.dp))
		}
		Box(

		) {
			CongestionGraphBackground(
				onGraphBgLineGlobalPositioned = { index, y ->
					val storedHeight = congestionBgBarHeightMap.getOrDefault(index, null)
					val lineHeight = with(density) { y.toDp() }
					if (storedHeight != null && lineHeight == storedHeight) return@CongestionGraphBackground
					congestionBgBarHeightMap[index] = lineHeight

					if (
						congestionBgBarHeightMap.containsKey(0) &&
						congestionBgBarHeightMap.containsKey(1) &&
						congestionBgBarHeightMap.containsKey(2) &&
						congestionBgBarHeightMap.containsKey(3) &&
						congestionBgBarHeightMap.containsKey(4)
					) {
						showGraphBar = true
					}
				}
			)

			if (showGraphBar) {
				CongestionGraphBars(
					congestionDataList = congestionDataList,
					congestionBgBarHeightMap = congestionBgBarHeightMap,
					onCongestionTagPositioned = {
						congestionTagOffsetX = it
					}
				)
			}
		}
	}

}

@Composable
private fun CongestionGraphBars(
	modifier: Modifier = Modifier,
	congestionDataList: ImmutableList<CongestionData>,
	congestionBgBarHeightMap: Map<Int, Dp>,
	onCongestionTagPositioned: (offsetDp: Dp) -> Unit = { }
) {
	val density = LocalDensity.current
	var graphBarAreaWidth by remember { mutableStateOf(0.dp) }
	val graphBarWidth = 14.dp
	var spacerWidth = (graphBarAreaWidth - graphBarWidth * 16) / 15

	LazyRow(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 12.dp)
			.onGloballyPositioned {
				with(density) {
					graphBarAreaWidth = it.size.width.toDp()
				}
			}
	) {
		itemsIndexed(
			congestionDataList
		) { index, congestionData ->
			key(congestionData.time) {
				CongestionGraphBar(
					modifier = Modifier.onGloballyPositioned {
						if (index == 1) {
							onCongestionTagPositioned(
								with(density) { it.positionInRoot().x.toDp() } - graphBarWidth / 2
							)
						}
					},
					width = graphBarWidth,
					height = getGraphBarHeight(congestionData.level, congestionBgBarHeightMap),
					offsetY = getGraphBarOffsetY(congestionData.level, congestionBgBarHeightMap),
					congestionLevel = congestionData.level
				)

				if (index != congestionDataList.lastIndex) {
					Spacer(modifier = Modifier.width(spacerWidth))
				}
			}

		}
	}
}

private fun getGraphBarOffsetY(
	level: CongestionLevel,
	congestionBgBarHeightMap: Map<Int, Dp>
): Dp = congestionBgBarHeightMap[4]!! - congestionBgBarHeightMap[0]!! - getGraphBarHeight(level, congestionBgBarHeightMap)

private fun getGraphBarHeight(
	level: CongestionLevel,
	congestionBgBarHeightMap: Map<Int, Dp>
): Dp = when (level) {
	CongestionLevel.LOW -> {
		congestionBgBarHeightMap[4]!! - congestionBgBarHeightMap[3]!!
	}

	CongestionLevel.MID -> {
		congestionBgBarHeightMap[4]!! - congestionBgBarHeightMap[2]!!
	}

	CongestionLevel.HIGH -> {
		congestionBgBarHeightMap[4]!! - congestionBgBarHeightMap[1]!!
	}

	CongestionLevel.MAX -> {
		congestionBgBarHeightMap[4]!! - congestionBgBarHeightMap[0]!!
	}
}


@Composable
private fun CongestionGraphBackground(
	onGraphBgLineGlobalPositioned: (index: Int, offset: Float) -> Unit
) {
	Column(
		verticalArrangement = Arrangement.spacedBy(19.dp),
		modifier = Modifier.fillMaxWidth()
	) {
		for (i in 0 until 5) {
			Spacer(
				Modifier
					.fillMaxWidth()
					.height(1.dp)
					.background(
						if (i == 4) WeskiColor.Gray40 else WeskiColor.Gray30
					)
					.onGloballyPositioned {
						onGraphBgLineGlobalPositioned(i, it.positionInRoot().y)
					}
			)
		}
	}
}

@Composable
private fun CongestionGraphBar(
	modifier: Modifier = Modifier,
	width: Dp,
	height: Dp,
	offsetY: Dp,
	congestionLevel: CongestionLevel
) {
	Spacer(
		modifier = modifier
			.width(width)
			.height(height)
			.offset(y = offsetY)
			.background(
				color = congestionLevel.presentColor,
				shape = RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp)
			)
	)
}

@DevicePreviews
@ThemePreviews
@Composable
private fun CongestionGraphPreview() {
	Column {
		Spacer(modifier = Modifier.height(100.dp))
		CongestionGraph(
			congestionDataList = persistentListOf<CongestionData>(
				CongestionData(
					time = 8,
					level = CongestionLevel.LOW,
					isPast = false
				),

				CongestionData(
					time = 10,
					level = CongestionLevel.MAX,
					isPast = false
				),

				CongestionData(
					time = 12,
					level = CongestionLevel.MID,
					isPast = false
				)
			)
		)
	}

}

@DevicePreviews
@ThemePreviews
@Composable
private fun CongestionGraphBarPreview() {
	CongestionGraphBar(
		width = 12.dp,
		height = 100.dp,
		offsetY = 10.dp,
		congestionLevel = CongestionLevel.LOW
	)
}