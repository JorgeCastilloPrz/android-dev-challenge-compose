/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.ui.theme.secondary
import com.example.androiddevchallenge.ui.theme.secondaryLight
import com.example.androiddevchallenge.ui.theme.text

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
                color = text,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = arcWidth)
            )

            drawArc(
                brush = Brush.sweepGradient(listOf(secondary, secondaryLight)),
                startAngle = 0f,
                sweepAngle = remainingPercent * 360f,
                useCenter = false,
                style = Stroke(width = arcWidth * 1.1f)
            )
        }
    )
}

@Preview
@Composable
fun CountdownArcPreview() {
    CountdownTimerTheme {
        CountdownArc(totalTimeSeconds = 150, remainingTimeSeconds = 100)
    }
}
