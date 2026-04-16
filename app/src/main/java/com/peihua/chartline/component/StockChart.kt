package com.peihua.chartline.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.drawable.toDrawable
import com.github.wangyiqian.stockchart.StockChart
import com.github.wangyiqian.stockchart.StockChartConfig
import com.peihua8858.compose.tools.rememberState
import com.peihua8858.compose.tools.toPx
import kotlin.math.roundToInt

@Composable
fun StockChart(
    modifier: Modifier = Modifier,
    updateConfig: StockChart.(config: StockChartConfig) -> Unit
) {
    val stockConfig by rememberState(StockChartConfig())
    AndroidView(
        modifier = modifier,
        factory = {
            val view = StockChart(it).apply {
                background = android.graphics.Color.WHITE.toDrawable()
                layoutParams = android.view.ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    200.dp.toPx(Density(context.resources.displayMetrics.density)).roundToInt()
                )
            }
            view.apply {
                updateConfig(stockConfig)
                setConfig(stockConfig)
            }
        },
        update = { view ->
            // Draw once the view has a size; avoids blank screen on first compose.
            view.post {
                view.updateConfig(stockConfig)
                view.setConfig(stockConfig)
            }
        }
    )
}
