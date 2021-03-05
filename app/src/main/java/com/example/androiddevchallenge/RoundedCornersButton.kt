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

import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.buttons

@Composable
fun RoundedCornersButton(
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = Modifier
            .padding(16.dp)
            .size(56.dp)
            .shadow(1.dp, RoundedCornerShape(CornerSize(16.dp)))
            .clip(RoundedCornerShape(CornerSize(16.dp))),
        onClick = {
            onClick()
        },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = buttons,
            disabledBackgroundColor = buttons
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.content_description_play_button)
        )
    }
}
