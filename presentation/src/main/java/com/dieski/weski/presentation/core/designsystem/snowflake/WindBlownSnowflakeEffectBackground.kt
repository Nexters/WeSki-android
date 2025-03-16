package com.dieski.weski.presentation.core.designsystem.snowflake

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.model.Snowflake
import com.dieski.weski.presentation.core.util.dpToPx
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
fun WindBlownSnowflakeEffectBackground(
	modifier: Modifier = Modifier
) {
	val amplitude = 6f
	val isRunning = remember { mutableStateOf(true) }

	BoxWithConstraints {
		val screenWidth: Int = maxWidth.dpToPx().toInt()
		val screenHeight: Int = maxHeight.dpToPx().toInt()

		AndroidView(
			modifier = modifier.fillMaxSize(),
			factory = { context ->
				SurfaceView(context).apply {
					holder.setFormat(PixelFormat.TRANSLUCENT)

					val paint = Paint().apply {
						color = Color.WHITE
						style = Paint.Style.FILL
						isAntiAlias = true
					}

					val bitmapCache = ResourcesCompat.getDrawable(
						context.resources, R.drawable.snow_texture, null
					)?.let { drawableToBitmap(it, 45, 45) }

					val backgroundBitmap = ResourcesCompat.getDrawable(
						context.resources, R.drawable.img_background, null
					)?.let { drawableToBitmap(it, screenWidth, screenHeight) }

					val snowflakes = List(200) {
						Snowflake(
							offset = Offset(
								Random.nextInt(screenWidth).toFloat(),
								Random.nextInt(screenHeight).toFloat()
							),
							angle = Random.nextFloat() * (PI / 2).toFloat() + (PI / 4).toFloat(),
							createTime = System.currentTimeMillis()
						)
					}

					val drawRunnable = object : Runnable {
						override fun run() {
							if (!isRunning.value) return

							snowflakes.forEach { flake ->
								val angle = flake.angle
								val newX = flake.offset.x + amplitude * cos(angle)
								val newY = flake.offset.y + amplitude * sin(angle)

								flake.offset = if (newY >= screenHeight) {
									Offset(Random.nextInt(screenWidth).toFloat(), 0f)
								} else {
									Offset(newX, newY)
								}
							}

							val canvas = holder.lockCanvas()
							canvas?.apply {
								drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)

								backgroundBitmap?.let { drawBitmap(it, 0f, 0f, paint) }

								snowflakes.forEach { flake ->
									bitmapCache?.let {
										drawBitmap(it, flake.offset.x, flake.offset.y, paint)
									}
								}

								holder.unlockCanvasAndPost(this)
							}

							postDelayed(this, 12L) // 60FPS 유지
						}
					}

					holder.addCallback(object : SurfaceHolder.Callback {
						override fun surfaceCreated(holder: SurfaceHolder) {
							isRunning.value = true
							post(drawRunnable)
						}

						override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

						override fun surfaceDestroyed(holder: SurfaceHolder) {
							isRunning.value = false
						}
					})
				}
			}
		)
	}

}

fun drawableToBitmap(
	drawable: Drawable,
	bitmapWidth: Int,
	bitmapHeight: Int
): Bitmap {
	if (drawable is BitmapDrawable) {
		return drawable.bitmap
	}

	val bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
	val canvas = Canvas(bitmap)
	drawable.setBounds(0, 0, canvas.width, canvas.height)
	drawable.draw(canvas)

	return bitmap
}