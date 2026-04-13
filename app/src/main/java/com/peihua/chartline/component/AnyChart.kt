package com.peihua.chartline.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.anychart.AnyChartView
import com.anychart.core.Chart

@Composable
fun AnyChartView(modifier: Modifier = Modifier, fetchData: () -> Chart) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            val view = AnyChartView(it).apply {
                layoutParams = android.view.ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            view.apply {
                setChart(fetchData())
            }
        },
        update = { view ->
            // Draw once the view has a size; avoids blank screen on first compose.
            view.post {
                view.setChart(fetchData())
            }
        }
    )
}