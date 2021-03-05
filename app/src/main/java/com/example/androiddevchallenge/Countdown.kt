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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.ui.theme.secondaryLight
import com.example.androiddevchallenge.ui.theme.text

@Composable
fun Countdown(totalTimeSeconds: Long, remainingTimeSeconds: Long, onTimerClick: () -> Unit) {
    val printableMinutes = remainingTimeSeconds / 60
    val printableSeconds = remainingTimeSeconds - printableMinutes * 60
    val colorState = remember { mutableStateOf(secondaryLight) }
    val animatedCircleColor = animateColorAsState(targetValue = colorState.value)

    Box(
        modifier = Modifier
            .size(320.dp)
            .clickable { onTimerClick() },
        contentAlignment = Alignment.Center,
    ) {
        CountdownArc(totalTimeSeconds, remainingTimeSeconds) { currentColor ->
            colorState.value = currentColor
        }

        val animateBubbleSize by animateIntAsState(
            targetValue = if (remainingTimeSeconds % 2 == 0L) 220 else 240,
            animationSpec = infiniteRepeatable(tween(250), repeatMode = RepeatMode.Reverse)
        )

        Box(
            modifier = Modifier
                .size(animateBubbleSize.dp)
                .clip(CircleShape)
                .background(animatedCircleColor.value),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                val timeText = with(AnnotatedString.Builder("$printableMinutes")) {
                    pushStyle(SpanStyle(fontSize = 24.sp))
                    append("m")
                    pop()
                    append(" $printableSeconds")
                    pushStyle(SpanStyle(fontSize = 24.sp))
                    append("s")
                    toAnnotatedString()
                }
                Text(
                    timeText,
                    style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Black),
                    color = text
                )
                Text("LEFT", style = MaterialTheme.typography.button, color = text)
            }
        }
    }
}

@Preview
@Composable
fun CountDownPreview() {
    CountdownTimerTheme {
        Countdown(totalTimeSeconds = 150, remainingTimeSeconds = 100) {}
    }
}
