package com.peihua.chartline.screen.ycharts

import android.content.Context
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.model.StatsDetail

@Composable
fun YChartsLineContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    LineChart(
        modifier = modifier.heightIn(min = 400.dp),
        lineChartData = data.yChartsModelLineData(context),
    )
}

private fun StatsDetail<Entry>.yChartsModelLineData(context: Context): LineChartData {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Point>()
    transactionsEntries.forEach {
//        val time = it.x.format("HH:mm:ss")
        values.add(Point(it.x, it.y))
    }
    return LineChartData(linePlotData = LinePlotData(lines = mutableListOf(Line(values))))
}