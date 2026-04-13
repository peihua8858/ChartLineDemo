package com.peihua.chartline.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AAOptions
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.peihua8858.compose.tools.rememberDerivedStateOf


@Composable
fun AAChartView(modifier: Modifier = Modifier, data: AAChartModel) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            AAChartView(it).apply {
                layoutParams = android.view.ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { view ->
            // Draw once the view has a size; avoids blank screen on first compose.
            view.post {
                view.aa_drawChartWithChartModel(data)
            }
        }
    )
}

@Composable
fun AAChartView(modifier: Modifier = Modifier, options: AAOptions, fetchData: (AAChartView?) -> Array<AASeriesElement>) {
    var chartViewRef by remember { mutableStateOf<AAChartView?>(null) }
    val data by rememberDerivedStateOf { fetchData(chartViewRef) }
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            AAChartView(ctx).apply {
                chartViewRef = this
                layoutParams = android.view.ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { view ->
            // Draw once the view has a size; avoids blank screen on first compose.
            view.post {
                view.aa_refreshChartWithChartOptions(options)
                view.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(data)
            }
        }
    )
}