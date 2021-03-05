package com.example.androiddevchallenge

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun CountdownScreen(insertedTimeState: MutableState<InsertedTimeState>) {
    Card(Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(id = R.string.hint)
        )
    }

    CountdownTimer(
        insertedTimeState.value.minutes,
        insertedTimeState.value.seconds
    ) { insertedTimeState.value = insertedTimeState.value.copy(showDialog = true) }
}
