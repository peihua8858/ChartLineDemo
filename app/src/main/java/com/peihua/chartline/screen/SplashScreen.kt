package com.peihua.chartline.screen

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.R
import com.peihua.chartline.ui.theme.ChartLineTheme

@Composable
fun SplashScreen(
    navController: NavController? = null,
    alphaAnimationTargetValue: Float = 0F,
    alphaAnimationDurationMillis: Int = 1000
) {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            var targetValue by remember {
                mutableFloatStateOf(alphaAnimationTargetValue)
            }

            val alpha by animateFloatAsState(
                targetValue = targetValue,
                animationSpec = tween(alphaAnimationDurationMillis),
                finishedListener = {
                    navController?.popBackStack()
                    navController?.navigate(LineChartRoute.Main.value)
                },
            )
            Image(
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = null,
                modifier = Modifier.alpha(alpha)
            )
            LaunchedEffect(key1 = Unit, block = { targetValue = 1F })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    ChartLineTheme {
        SplashScreen(alphaAnimationTargetValue = 1F)
    }
}