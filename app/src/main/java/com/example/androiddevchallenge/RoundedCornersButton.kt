package com.example.androiddevchallenge

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCornersButton(
  icon: ImageVector,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true
) {
  Button(
    modifier = modifier
      .size(56.dp)
      .shadow(1.dp, RoundedCornerShape(CornerSize(16.dp)))
      .clip(RoundedCornerShape(CornerSize(16.dp))),
    onClick = {
      onClick()
    },
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
  ) {
    Icon(
      imageVector = icon,
      contentDescription = stringResource(id = R.string.content_description_play_button)
    )
  }
}