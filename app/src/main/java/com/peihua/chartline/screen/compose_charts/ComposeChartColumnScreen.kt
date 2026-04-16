package com.peihua.chartline.screen.compose_charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties

@Composable
fun ComposeChartColumnContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    ColumnChart(
        labelProperties = LabelProperties(enabled = false),
        labelHelperProperties = LabelHelperProperties(enabled = false),
        data = data.modelTolColumnData(),
    )
}

private fun StatsDetail<Entry>.modelTolColumnData(): List<Bars> {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Bars.Data>()
    transactionsEntries.forEach {
        val time = it.x.format("HH:mm:ss")
        values.add(
            Bars.Data(
                label = time, value = it.y.toDouble(),
                color = Brush.verticalGradient(listOf(Color.Red, Color.Blue))
            )
        )
    }
    val bars = Bars(label = "transactions_per_second", values = values)
    return listOf(bars)
}
