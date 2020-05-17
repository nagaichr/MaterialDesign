package com.includehelp.saviorstj_android.fragments.camera

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import timber.log.Timber

class FacePointsView @JvmOverloads constructor(context: Context,
                      attrs: AttributeSet? = null,
                      defStyleAttr: Int = -1) : View(context, attrs, defStyleAttr) {

  private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = Color.GREEN
    style = Paint.Style.STROKE
    strokeWidth=8F
  }

  var points = listOf<PointF>()
    set(value) {
      field = value
      transformPoints()
    }

  var transform = Matrix()
    set(value) {
      field = value
      transformPoints()
    }

  private var drawingPoints = listOf<PointF>()
    set(value) {
      field = value
      invalidate()
    }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    canvas.apply {
      for (point in drawingPoints) {
//        Timber.e("X="+point.x+" , Y="+point.y)
        drawCircle(point.x, point.y, 8F, pointPaint)

        //drawRect(258F,521F,598F,862F,pointPaint)
      }
    }
  }

  private fun transformPoints() {
    // build src and dst
    val transformInput = points.flatMap { listOf(it.x, it.y) }.toFloatArray()
    val transformOutput = FloatArray(transformInput.size)

    // apply the matrix transformation
    transform.mapPoints(transformOutput, transformInput)

    // convert transformed FloatArray to List<Point>
    drawingPoints = transformOutput.asList()
      .chunked(size = 2, transform = { (x, y) -> PointF(x, y) })
  }
}