package com.peihua.chartline.screen.netguru

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.data.Entry
import com.netguru.multiplatform.charts.grid.axisscale.YAxisScale
import com.netguru.multiplatform.charts.line.LineChart
import com.netguru.multiplatform.charts.line.LineChartData
import com.netguru.multiplatform.charts.line.LineChartPoint
import com.netguru.multiplatform.charts.line.LineChartSeries
import com.netguru.multiplatform.charts.line.lineChartColors
import com.netguru.multiplatform.charts.round
import com.netguru.multiplatform.charts.theme.LocalChartColors
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import com.peihua8858.tools.utils.dLog
import korlibs.time.DateTime
import korlibs.time.TimeSpan
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun NetguruChartLineContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val lineData = remember {
        LineChartData(
            series = (1..1).map {
                LineChartSeries(
                    dataName = "data $it",
                    lineColor = listOf(
                        Color.Yellow,
                        Color.Red,
                        Color.Blue,
                    )[it - 1],
                    listOfPoints = (1..10).map { point ->
                        LineChartPoint(
                            x = DateTime.now().minus(TimeSpan(point * 24 * 60 * 60 * 1000.0)).unixMillisLong,
                            y = (1..15).random().toFloat(),
                        )
                    }
                )
            },
        )
    }
    lineData.apply {
        dLog { "modelToLineData>222>>>YAxisScale>>maxY:${this.maxY},minY:${this.minY}" }
        YAxisScale(
            min = minY,
            max = maxY,
            maxTickCount = 4,
            roundClosestTo = 10,
        ).apply {
            dLog { "modelToLineData>222>>>YAxisScale>>max:${this.max},min:${this.min},tick:$tick" }
        }
    }
    val colors = LocalChartColors.current.copy(grid = Color.Gray, surface = Color.White)
    val lineChartData = data.modelToLineData()
    val maxY = lineChartData.maxY
    val minY = lineChartData.minY
    val round = (maxY- minY).roundToInt()
    LineChart(
        modifier = modifier.heightIn(min = 400.dp),
        colors = colors.lineChartColors,
        maxVerticalLines = 12,
        maxHorizontalLines = 10,
        roundMinMaxClosestTo = 3,
        yAxisLabel = { value ->
            Text(
                fontSize = 12.sp,
                text = NumberFormat.getInstance().format(value),
                textAlign = TextAlign.Center
            )
        },
        xAxisLabel = { value ->
            Text(
                fontSize = 12.sp,
                text = value.format("HH:mm:ss"),
                textAlign = TextAlign.Center
            )
        },
        overlayHeaderLabel = { value ->
            Text(
                fontSize = 12.sp,
                text = value.format("HH:mm:ss"),
                textAlign = TextAlign.Center
            )
        },
        overlayDataEntryLabel = { dataName, value ->
            Text(
                fontSize = 12.sp,
                text = "$dataName: ${NumberFormat.getInstance(Locale.US).format(value)}",
                textAlign = TextAlign.Center
            )
        },
        lineChartData = lineChartData,
    )

}

private fun List<LineChartPoint>.getMinMaxTimestamp(): Pair<Long, Long> {
    val sortedTimestamp = sortedBy { it.x }
    return Pair(sortedTimestamp.first().x, sortedTimestamp.last().x)
}

private fun List<LineChartPoint>.getMinMaxValue(): Pair<Float, Float> {
    val sortedValue = sortedBy { it.y }
    return Pair(sortedValue.first().y, sortedValue.last().y)
}

private fun StatsDetail<Entry>.modelToLineData(): LineChartData {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<LineChartPoint>()
//    val labels = mutableListOf<String>()
    transactionsEntries.forEach {
//        val time = it.x.format("HH:mm:ss")
//        labels.add(time)
        values.add(LineChartPoint((it.x * 1000).toLong(), it.y))
    }
    val minMaxValue = values.getMinMaxValue()
    val minMaxTimestamp = values.getMinMaxTimestamp()
    dLog { "modelToLineData>>>minMaxValue:$minMaxValue,minMaxTimestamp:$minMaxTimestamp" }
    val lineChartSeries: List<LineChartSeries> = listOf(
        LineChartSeries(
            dataName = "transactions_per_second",
            listOfPoints = values,
            lineColor = Color.Red,
        )
    )
    return LineChartData(lineChartSeries).apply {
        val roundClosestTo1 = (maxY.roundToInt()- minY.roundToInt())
        dLog { "modelToLineData>>>maxX:$maxX,minX:$minX,maxY:$maxY,minY:$minY,roundClosestTo1:$roundClosestTo1" }
        val roundClosestTo = (maxY- minY).roundToInt()
        YAxisScale(
            min = minY,
            max = maxY,
            maxTickCount = 10,
            roundClosestTo = 3,
        ).apply {
            dLog { "modelToLineData>>>>YAxisScale>>max:${this.max},min:${this.min},tick:$tick,roundClosestTo:$roundClosestTo" }
        }
    }
}