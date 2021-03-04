package com.example.androiddevchallenge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme

@Composable
fun Countdown(totalTimeSeconds: Long, remainingTimeSeconds: Long) {
  val printableMinutes = remainingTimeSeconds / 60
  val printableSeconds = remainingTimeSeconds - printableMinutes * 60

  Box(
    modifier = Modifier.size(320.dp),
    contentAlignment = Alignment.Center,
  ) {
    CountdownArc(totalTimeSeconds, remainingTimeSeconds)

    Box(
      modifier = Modifier
        .size(220.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colors.primaryVariant),
      contentAlignment = Alignment.Center
    ) {
      Column(horizontalAlignment = CenterHorizontally) {
        Text(
          "${printableMinutes}m ${printableSeconds}s",
          style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Black)
        )
        Text("LEFT", style = MaterialTheme.typography.button)
      }
    }
  }
}

@Preview
@Composable
fun CountDownPreview() {
  CountdownTimerTheme {
    Countdown(totalTimeSeconds = 150, remainingTimeSeconds = 100)
  }
}