package com.example.androiddevchallenge

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
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Card {
            Column(Modifier.padding(16.dp)) {
                Text(stringResource(id = R.string.dialog_text))
                Row(Modifier.padding(top = 8.dp)) {
                    TextField(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        value = state.value.minutes.toString(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Done
                        ),
                        label = {
                            Text(
                                text = "Minutes",
                                style = MaterialTheme.typography.body2
                            )
                        },
                        onValueChange = { state.value = state.value.copy(minutes = it.toInt()) }
                    )

                    TextField(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        value = state.value.seconds.toString(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Done
                        ),
                        label = {
                            Text(
                                text = "Seconds",
                                style = MaterialTheme.typography.body2
                            )
                        },
                        onValueChange = { state.value = state.value.copy(seconds = it.toInt()) }
                    )
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