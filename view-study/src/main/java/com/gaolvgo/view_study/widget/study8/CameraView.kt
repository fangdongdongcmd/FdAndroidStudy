package com.gaolvgo.view_study.widget.study8

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.gaolvgo.view_study.R
import com.gaolvgo.view_study.utils.px2dp

private val BITMAP_SIZE = 200.px2dp()
private val BITMAP_PADDING = 100.px2dp()

class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  private val bitmap = getAvatar(BITMAP_SIZE.toInt())
  private val camera = Camera()

  var topFlip = 0f
    set(value) {
      field = value
      invalidate()
    }
  var bottomFlip = 30f
    set(value) {
      field = value
      invalidate()
    }
  var flipRotation = 0f
    set(value) {
      field = value
      invalidate()
    }

  init {
    camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
  }

  override fun onDraw(canvas: Canvas) {

    // 上半部分
    canvas.save()
    canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
    canvas.rotate(-flipRotation)
    camera.save()
    camera.rotateX(topFlip)
    camera.applyToCanvas(canvas)
    camera.restore()
    canvas.clipRect(-BITMAP_SIZE, -BITMAP_SIZE,
      BITMAP_SIZE, 0f)
    canvas.rotate(flipRotation)
    canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
    canvas.drawBitmap(bitmap,
      BITMAP_PADDING,
      BITMAP_PADDING, paint)
    canvas.restore()

    // 下半部分
    canvas.save()
    canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
    canvas.rotate(-flipRotation)
    camera.save()
    camera.rotateX(bottomFlip)
    camera.applyToCanvas(canvas)
    camera.restore()
    canvas.clipRect(-BITMAP_SIZE, 0f,
      BITMAP_SIZE,
      BITMAP_SIZE
    )
    canvas.rotate(flipRotation)
    canvas.translate(- (BITMAP_PADDING + BITMAP_SIZE / 2), - (BITMAP_PADDING + BITMAP_SIZE / 2))
    canvas.drawBitmap(bitmap,
      BITMAP_PADDING,
      BITMAP_PADDING, paint)
    canvas.restore()
  }

  private fun getAvatar(width: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources,
      R.drawable.batman, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(resources,
      R.drawable.batman, options)
  }
}