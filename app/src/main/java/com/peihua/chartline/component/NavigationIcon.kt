package com.peihua.chartline.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


/**
 * 导航图标
 */
@Composable
fun NavigationIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.AutoMirrored.Default.ArrowBackIos,
    navigateUp: () -> Unit,
) {
    NavigationIcon(
        modifier
            .size(32.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        imageVector,
        tintColor = Color.Black,
        navigateUp = navigateUp
    )
}

/**
 * 导航图标
 */
@Composable
fun NavigationIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.AutoMirrored.Default.ArrowBackIos,
    tintColor: Color = Color.Black,
    navigateUp: () -> Unit,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { navigateUp() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .aspectRatio(1f)
                .fillMaxSize(),
            imageVector = imageVector,
            tint = tintColor,
            contentDescription = ""
        )
    }
}