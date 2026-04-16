package com.peihua.chartline.screen.compose_charts

import android.content.Context
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.model.StatsDetail
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.ZeroLineProperties

@Composable
fun ComposeChartLineContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    LineChart(
        modifier = modifier.heightIn(min = 300.dp),
        gridProperties = GridProperties(
            enabled = true,
            yAxisProperties = GridProperties.AxisProperties(enabled = true, lineCount = 8),
            xAxisProperties = GridProperties.AxisProperties(enabled = true, lineCount = 8)
        ),
        zeroLineProperties = ZeroLineProperties(enabled = true),
        labelHelperProperties = LabelHelperProperties(enabled = false),
        labelProperties = LabelProperties(enabled = true),
        dotsProperties = DotProperties(enabled = true),
        data = data.modelTolLineData(context),
    )

}

private fun StatsDetail<Entry>.modelTolLineData(context: Context): List<Line> {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Double>()
    transactionsEntries.forEach {
//        val time = it.x.format("HH:mm:ss")
        values.add(it.y.toDouble())
    }
    val line = Line(label = "transactions_per_second", values = values, color = Brush.verticalGradient(listOf(Color.Red, Color.Blue)))
    return listOf(line)
}