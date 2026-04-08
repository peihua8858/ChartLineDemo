package com.peihua.chartline.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp


@Composable
fun RoundTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .widthIn(min = 128.dp),
        onClick = onClick) {
        ScaleText(
            text = text,
            style = typography.titleMedium,
        )
    }
}