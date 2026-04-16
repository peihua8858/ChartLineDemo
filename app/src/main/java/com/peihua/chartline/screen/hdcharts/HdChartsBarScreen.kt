package com.peihua.chartline.screen.hdcharts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import io.github.dautovicharis.charts.BarChart
import io.github.dautovicharis.charts.model.ChartDataSet
import io.github.dautovicharis.charts.model.toChartDataSet
import io.github.dautovicharis.charts.style.BarChartDefaults

@Composable
 fun HdChartsBarContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    BarChart(
        dataSet = data.hdChartsModelBarData(context),
        style = BarChartDefaults.style(),
    )
}

private fun StatsDetail<Entry>.hdChartsModelBarData(context: android.content.Context): ChartDataSet {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Float>()
    val labels = mutableListOf<String>()
    transactionsEntries.forEach {
        val time = it.x.format("HH:mm:ss")
        values.add(it.y)
        labels.add(time)
    }
    return values.toChartDataSet(title = "transactions_per_second", labels = labels)
}
