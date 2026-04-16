package com.peihua.chartline.screen.hdcharts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import io.github.dautovicharis.charts.LineChart
import io.github.dautovicharis.charts.model.ChartDataSet
import io.github.dautovicharis.charts.model.toChartDataSet
import io.github.dautovicharis.charts.style.LineChartDefaults

@Composable
fun HdChartsLineContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    LineChart(
        style = LineChartDefaults.style(
            pointVisible = false,
            zoomControlsVisible = false

        ),
        dataSet = data.hdChartsModelLineData(context)
    )
}

private fun StatsDetail<Entry>.hdChartsModelLineData(context: android.content.Context): ChartDataSet {
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