package com.dieski.weski.presentation.core.designsystem.snowflake

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
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
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import com.dieski.weski.presentation.R
import com.dieski.weski.presentation.core.model.Snowflake
import com.dieski.weski.presentation.core.util.dpToPx
import java.util.concurrent.atomic.AtomicBoolean
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
	val isRunning = AtomicBoolean(true)

	BoxWithConstraints {
		val screenWidth: Int = maxWidth.dpToPx().toInt()
		val screenHeight: Int = maxHeight.dpToPx().toInt()

		AndroidView(
			modifier = modifier.fillMaxSize(),
			factory = { context ->
				Log.e("Test@@@", "maxWidth:$maxWidth screenWidth:$screenWidth")
				SurfaceView(context).apply {
					holder.setFormat(PixelFormat.TRANSLUCENT)

					val paint = Paint().apply {
						color = Color.WHITE
						style = Paint.Style.FILL
					}
					val snowflakes = mutableListOf<Snowflake>()
					val handler = Handler(Looper.getMainLooper())
					val choreographer = android.view.Choreographer.getInstance()

					val snowCount = if (screenWidth > 1500) {
						400
					} else if (screenWidth > 1000){
						300
					} else {
						200
					}
					val bitmapList = mutableListOf<Bitmap>()
					for (i in 0 until snowCount) {
						val drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.snow_texture, null)
						if (drawable != null) {
							val bitmap: Bitmap = drawableToBitmap(drawable, 45, 45)
							bitmapList.add(bitmap)
						}
					}

					bitmapList.forEach { _ ->
						val x = Random.nextInt(screenWidth).toFloat()
						val y = Random.nextInt(screenHeight).toFloat()

						val snowflake = Snowflake(
							offset = Offset(x, -y),
							angle = Random.nextFloat() * (PI / 2).toFloat() + (PI / 4).toFloat(),
							createTime = System.currentTimeMillis()
						)

						snowflakes.add(snowflake)
					}

					val drawSnowflake = object : Runnable {
						override fun run() {
							if (!isRunning.get()) return

							snowflakes.forEach {
								val angle = it.angle
								val horizontalOffset = amplitude * cos(angle)
								val verticalOffset = amplitude * sin(angle)

								val newX = it.offset.x + horizontalOffset
								val newY = it.offset.y + verticalOffset

								if (newY >= screenHeight) {
									val x = Random.nextInt(screenWidth).toFloat()
									val y = Random.nextInt(screenHeight).toFloat()

									it.offset = Offset(x, -y)
								} else {
									it.offset = Offset(newX, newY)
								}
							}

							val bg = drawableToBitmap(
								ResourcesCompat.getDrawable(context.resources, R.drawable.img_background, null)!!,
								screenWidth,
								screenHeight
							)
							val canvas: Canvas? = holder.lockHardwareCanvas()
							canvas?.let {
								it.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
								it.drawBitmap(bg, Matrix(), paint)

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
							isRunning.set(true)
							handler.post(drawSnowflake)
						}

						override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

						override fun surfaceDestroyed(holder: SurfaceHolder) {
							isRunning.set(false)
							handler.removeCallbacks(drawSnowflake)
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