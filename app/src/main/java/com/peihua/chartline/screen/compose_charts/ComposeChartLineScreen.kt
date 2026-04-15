package com.peihua.chartline.screen.compose_charts

import android.content.Context
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoScrollState
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua8858.tools.model.ResultData
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
    timeSpan: QuoteTimeSpan,
    averageItem: RollingAverage,
    state: ResultData<StatsDetail<Entry>>,
    refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is ResultData.Success -> {
                LineChart(
                    modifier = Modifier.heightIn(min = 300.dp),
                    gridProperties = GridProperties(enabled = true,
                        yAxisProperties = GridProperties.AxisProperties(enabled = true, lineCount = 8),
                        xAxisProperties = GridProperties.AxisProperties(enabled = true, lineCount = 8)),
                    zeroLineProperties = ZeroLineProperties(enabled = true),
                    labelHelperProperties = LabelHelperProperties(enabled = false),
                    labelProperties = LabelProperties(enabled = true),
                    dotsProperties = DotProperties(enabled = true),
                    data = state.data.modelTolLineData(context),
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