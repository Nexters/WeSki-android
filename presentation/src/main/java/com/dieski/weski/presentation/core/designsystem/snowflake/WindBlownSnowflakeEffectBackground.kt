package com.dieski.weski.presentation.core.designsystem.snowflake

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.model.Snowflake
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
	modifier: Modifier = Modifier,
	@DrawableRes backgroundImageRes: Int? = null
) {
	val configuration = LocalConfiguration.current
	val density = LocalDensity.current

	val screenWidthPx = with(density) {
		configuration.screenWidthDp * this.density
	}

	val screenHeightPx = with(density) {
		configuration.screenHeightDp * this.density
	}
	val amplitude = 6f

	AndroidView(
		modifier = modifier.fillMaxSize(),
		factory = { context ->
			SurfaceView(context).apply {
				holder.setFormat(PixelFormat.TRANSLUCENT)
//				setBackgroundResource(R.drawable.img_background)
				val paint = Paint().apply {
					color = Color.WHITE
					style = Paint.Style.FILL
				}
				val snowflakes = mutableListOf<Snowflake>()
				val handler = Handler(Looper.getMainLooper())
				val choreographer = android.view.Choreographer.getInstance()

				val bitmapList = mutableListOf<Bitmap>()
				for (i in 0 until 30) {
					val drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.snow_texture, null)
					if (drawable != null) {
						 val bitmap: Bitmap = drawableToBitmap(drawable, 30, 30)
						 bitmapList.add(bitmap)
					}
				}

				bitmapList.forEach {
					val x = Random.nextInt(screenWidthPx.toInt())
					val y = Random.nextInt(screenHeightPx.toInt()/2)

					val snowflake = Snowflake(
						offset = Offset(x.toFloat(), (-y).toFloat()),
						angle = Random.nextFloat() * (PI / 2).toFloat() + (PI / 4).toFloat(),
						createTime = System.currentTimeMillis()
					)

					snowflakes.add(snowflake)
				}

				val drawSnowflake = object : Runnable {
					override fun run() {
						snowflakes.forEach {
							val angle = it.angle
							val horizontalOffset = amplitude * cos(angle)
							val verticalOffset = amplitude * sin(angle)

							val newX = it.offset.x + horizontalOffset
							val newY = it.offset.y + verticalOffset

							if (newX <= 0 || newY >= screenHeightPx) {
								val x = Random.nextInt(screenWidthPx.toInt()).toFloat()
								val y = Random.nextInt(screenHeightPx.toInt() / 2).toFloat()

								it.offset = Offset(x, -y)
							} else {
								it.offset = Offset(newX, newY)
							}
						}

						val bg = drawableToBitmap(ResourcesCompat.getDrawable(context.resources, R.drawable.img_background, null)!!, screenWidthPx.toInt(), screenHeightPx.toInt())
						val canvas: Canvas? = holder.lockHardwareCanvas()
						canvas?.let {
							it.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
							it.drawRect(0f, 0f, screenWidthPx, screenHeightPx, Paint().apply {
								color = Color.YELLOW
							})

							it.drawBitmap(bg, 0f, 0f, paint)

							bitmapList.forEachIndexed { index, bitmap ->
								val light = snowflakes[index]
								it.drawBitmap(bitmap, light.offset.x, light.offset.y, paint)
							}

							holder.unlockCanvasAndPost(it)
						}

						choreographer.postFrameCallback { run() }
					}
				}

				holder.addCallback(object : SurfaceHolder.Callback {
					override fun surfaceCreated(holder: SurfaceHolder) {
						handler.post(drawSnowflake)
					}

					override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
						// Do nothing
					}

					override fun surfaceDestroyed(holder: SurfaceHolder) {
						// Do nothing
						handler.removeCallbacks(drawSnowflake)
					}
				})
			}
		}
	)
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
	canvas.drawColor(Color.YELLOW)
	drawable.setBounds(0, 0, canvas.width, canvas.height)
	drawable.draw(canvas)

	return bitmap
}