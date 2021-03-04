package com.example.androiddevchallenge

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CountdownTimer(totalMinutes: Int, totalSeconds: Int) {
  val scope = rememberCoroutineScope()
  val snackbarState = remember { SnackbarHostState() }
  val minutes = remember { mutableStateOf(totalMinutes) }
  val seconds = remember { mutableStateOf(totalSeconds) }

  val state = elapsedTimeAsState(totalMinutes = minutes.value, totalSeconds = seconds.value)

  if (state.value == 0L) {
    scope.launch {
      snackbarState.showSnackbar("Time! ⌛️", duration = SnackbarDuration.Short)
    }
  }

  DoneFeedback(snackbarState)

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Countdown(totalTimeSeconds(minutes.value, seconds.value), state.value)
      Row {
        RoundedCornersButton(
          modifier = Modifier.padding(16.dp),
          icon = if (state.value == totalTimeSeconds(
              totalMinutes,
              totalSeconds
            ) || state.value == 0L
          ) {
            Icons.Filled.PlayArrow
          } else {
            Icons.Filled.Refresh
          },
          onClick = {
            minutes.value = totalMinutes
            seconds.value = totalSeconds
          })

        RoundedCornersButton(
          modifier = Modifier.padding(16.dp),
          icon = Icons.Filled.Pause,
          enabled = state.value != totalTimeSeconds(
            totalMinutes,
            totalSeconds
          ) && state.value != 0L,
          onClick = {
            minutes.value = totalMinutes
            seconds.value = totalSeconds
          })
      }
    }
  }
}

@Composable
fun DoneFeedback(snackbarState: SnackbarHostState) {
  SnackbarHost(
    hostState = snackbarState,
    snackbar = { data ->
      Snackbar(
        modifier = Modifier.padding(16.dp),
        content = {
          Text(
            text = data.message,
            style = MaterialTheme.typography.body2
          )
        }
      )
    },
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight(Alignment.Bottom)
  )
}

/**
 * Uses [LaunchedEffect] to launch a suspended job that spans across recompositions unless its keys
 * change. The job loops until the total time is over, suspending every second via delay so the
 * output state can be updated with the remaining time in seconds.
 *
 * The keys for [LaunchedEffect] are the total minutes and seconds provided by the user, so the same
 * job will remain active across recompositions until new values are selected.
 */
@Composable
private fun elapsedTimeAsState(totalMinutes: Int, totalSeconds: Int): State<Long> {
  val totalDurationSeconds = totalTimeSeconds(totalMinutes, totalSeconds)
  val elapsedTimeState = remember { mutableStateOf(totalDurationSeconds) }

  LaunchedEffect(key1 = totalMinutes, key2 = totalSeconds) {
    while (elapsedTimeState.value > 0) {
      delay(1000)
      elapsedTimeState.value -= 1
    }
  }
  return elapsedTimeState
}

private fun totalTimeSeconds(minutes: Int, seconds: Int): Long = minutes * 60L + seconds