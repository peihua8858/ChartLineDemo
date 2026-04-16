package com.peihua.chartline.screen.compose_charts

import android.content.Context
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.model.StatsDetail

@Composable
fun ComposeChartBarContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    BarChart(
        modifier = modifier.heightIn(min = 400.dp),
        barChartData = data.yChartsModelLineData(context),
    )
}

private fun StatsDetail<Entry>.yChartsModelLineData(context: Context): BarChartData {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<BarData>()
    transactionsEntries.forEach {
//        val time = it.x.format("HH:mm:ss")
        values.add(BarData(Point(it.x, it.y)))
    }
    return BarChartData(chartData = values)
}