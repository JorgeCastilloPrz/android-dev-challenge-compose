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

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class InsertedTimeState(val minutes: Int, val seconds: Int, val showDialog: Boolean = false)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InsertTimeScreen(state: MutableState<InsertedTimeState>) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card {
            Column(Modifier.padding(16.dp)) {
                Text(stringResource(id = R.string.dialog_text))
                Row(Modifier.padding(top = 8.dp)) {
                    val inputModifier = Modifier
                        .weight(1f)
                        .padding(4.dp)

                    TimeInput(
                        state.value.minutes.toString(),
                        R.string.dialog_minutes_label,
                        inputModifier
                    ) {
                        if (it.isNotEmpty() && it.length <= 2) {
                            state.value = state.value.copy(minutes = it.toInt())
                        }
                    }

                    TimeInput(
                        state.value.seconds.toString(),
                        R.string.dialog_seconds_label,
                        inputModifier
                    ) {
                        if (it.isNotEmpty() && it.length <= 2) {
                            state.value = state.value.copy(seconds = it.toInt())
                        }
                    }
                }

                Divider(
                    Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
                )
                Button(
                    onClick = { state.value = state.value.copy(showDialog = false) },
                    shape = RectangleShape,
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(id = R.string.dialog_button))
                }
            }
        }
    }
}

@Composable
fun TimeInput(
    text: String,
    @StringRes labelRes: Int,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.NumberPassword,
        imeAction = ImeAction.Done
    )

    TextField(
        modifier = modifier,
        maxLines = 1,
        value = text,
        keyboardOptions = keyboardOptions,
        label = {
            Text(
                text = stringResource(id = labelRes),
                style = MaterialTheme.typography.body2
            )
        },
        onValueChange = onValueChange
    )
}
