package com.peihua.chartline.screen.hdcharts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import com.peihua8858.tools.model.ResultData
import io.github.dautovicharis.charts.RadarChart
import io.github.dautovicharis.charts.model.ChartDataSet
import io.github.dautovicharis.charts.model.toChartDataSet
import io.github.dautovicharis.charts.style.RadarChartDefaults


@Composable
 fun HdChartsRadarContent(
    modifier: Modifier = Modifier,
    timeSpan: QuoteTimeSpan,
    averageItem: RollingAverage,
    state: ResultData<StatsDetail<Entry>>,
    refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is ResultData.Success -> {
                RadarChart(
                    dataSet = state.data.hdChartsModelRadarData(context),
                    style = RadarChartDefaults.style(),
                )
            }

            is ResultData.Failure -> {
                ErrorView(
                    retry = {
                        refresh(timeSpan, averageItem)
                    })
            }

            is ResultData.Initialize -> {
                refresh(timeSpan, averageItem)
            }

            is ResultData.Starting -> {
                LoadingView(modifier = Modifier)
            }
        }
    }

}

private fun StatsDetail<Entry>.hdChartsModelRadarData(context: android.content.Context): ChartDataSet {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Float>()
    val labels = mutableListOf<String>()
    for (i in 0 until minOf(5, transactionsEntries.size)) {
        val item = transactionsEntries[i]
        val time = item.x.format("HH:mm:ss")
        values.add(item.y)
        labels.add(time)
    }
    return values.toChartDataSet(title = "transactions_per_second", labels = labels)
}

