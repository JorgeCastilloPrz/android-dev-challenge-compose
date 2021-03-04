package com.example.androiddevchallenge

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.ui.theme.egg
import com.example.androiddevchallenge.ui.theme.eggBright
import kotlin.math.atan

@Composable
fun CountdownArc(totalTimeSeconds: Long, remainingTimeSeconds: Long) {
  val remainingPercent = remainingTimeSeconds.toFloat() / totalTimeSeconds
  val arcWidth = with(LocalDensity.current) { 16.dp.toPx() }

  Canvas(
    modifier = Modifier
      .aspectRatio(1f)
      .padding(8.dp),
    onDraw = {
      drawArc(
        color = Color.Black,
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(width = arcWidth)
      )

      val capOffset = degreesRequiredForStrokeCap(arcWidth * 1.1f)

      drawArc(
        brush = Brush.sweepGradient(listOf(eggBright, egg)),
        startAngle = capOffset,
        sweepAngle = remainingPercent * 360f - capOffset,
        useCenter = false,
        style = Stroke(width = arcWidth * 1.1f, cap = StrokeCap.Round)
      )
    }
  )
}

private fun DrawScope.degreesRequiredForStrokeCap(strokeWidth: Float): Float {
  val adjacent = (size.minDimension - strokeWidth) / 2
  val opposite = strokeWidth / 2.0
  return Math.toDegrees(atan(opposite / adjacent)).toFloat()
}

@Preview
@Composable
fun CountdownArcPreview() {
  CountdownTimerTheme {
    CountdownArc(totalTimeSeconds = 150, remainingTimeSeconds = 100)
  }
}