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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.ui.theme.primary
import com.example.androiddevchallenge.ui.theme.text

@Composable
fun Countdown(totalTimeSeconds: Long, remainingTimeSeconds: Long) {
    val printableMinutes = remainingTimeSeconds / 60
    val printableSeconds = remainingTimeSeconds - printableMinutes * 60
    val colorState = remember { mutableStateOf(primary) }
    val animatedCircleColor = animateColorAsState(targetValue = colorState.value)

    Box(
        modifier = Modifier.size(320.dp),
        contentAlignment = Alignment.Center,
    ) {
        CountdownArc(totalTimeSeconds, remainingTimeSeconds) { currentColor ->
            colorState.value = currentColor
        }

        Box(
            modifier = Modifier
                .size(220.dp)
                .clip(CircleShape)
                .background(animatedCircleColor.value),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                Text(
                    "${printableMinutes}m ${printableSeconds}s",
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Black),
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
        Countdown(totalTimeSeconds = 150, remainingTimeSeconds = 100)
    }
}
