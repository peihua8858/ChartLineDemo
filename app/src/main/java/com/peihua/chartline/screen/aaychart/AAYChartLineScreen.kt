package com.peihua.chartline.screen.aaychart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aay.compose.baseComponents.model.LegendPosition
import com.aay.compose.lineChart.LineChart
import com.aay.compose.lineChart.model.LineParameters
import com.aay.compose.lineChart.model.LineType
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.utils.dLog

@Composable
fun AAYChartLineContent(
    modifier: Modifier = Modifier,
    timeSpan: QuoteTimeSpan,
    averageItem: RollingAverage,
    state: ResultData<StatsDetail<Entry>>,
    refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is ResultData.Success -> {
                val lineData = state.data.modelToLineData()
                LineChart(
                    modifier = Modifier.heightIn(min = 400.dp),
                    xAxisData = lineData.first,
                    showXAxis = true,
                    yAxisRange = 15,
                    linesParameters = lineData.second,
                    legendPosition = LegendPosition.BOTTOM

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

private fun StatsDetail<Entry>.modelToLineData(): Pair<List<String>, List<LineParameters>> {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Double>()
    val labels = mutableListOf<String>()
    transactionsEntries.forEach {
        val time = it.x.format("HH:mm:ss")
        labels.add(time)
        values.add(it.y.toDouble())
    }
    dLog { "modelToLineData:${values.size == labels.size}" }
    val testLineParameters: List<LineParameters> = listOf(
        LineParameters(
            label = "transactions_per_second:${values.size}",
            data = values,
            lineColor = Color.Red,
            lineType = LineType.CURVED_LINE,
            lineShadow = true
        )
    )
    return labels to testLineParameters
}