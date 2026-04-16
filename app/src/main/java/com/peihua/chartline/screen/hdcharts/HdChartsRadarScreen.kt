package com.peihua.chartline.screen.hdcharts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import io.github.dautovicharis.charts.RadarChart
import io.github.dautovicharis.charts.model.ChartDataSet
import io.github.dautovicharis.charts.model.toChartDataSet
import io.github.dautovicharis.charts.style.RadarChartDefaults


@Composable
 fun HdChartsRadarContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    RadarChart(
        dataSet = data.hdChartsModelRadarData(context),
        style = RadarChartDefaults.style(),
    )

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

