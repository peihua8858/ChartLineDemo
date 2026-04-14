package com.peihua.chartline.screen.vicocharts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.BaseAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.compose.cartesian.data.columnSeries
import com.patrykandpatrick.vico.compose.cartesian.data.lineSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.data.ExtraStore
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import com.peihua8858.tools.model.ResultData
import io.github.dautovicharis.charts.BarChart
import io.github.dautovicharis.charts.model.ChartDataSet
import io.github.dautovicharis.charts.model.toChartDataSet
import io.github.dautovicharis.charts.style.BarChartDefaults

private val BottomAxisLabelKey = ExtraStore.Key<List<String>>()
private val BottomAxisValueFormatter = CartesianValueFormatter { context, x, _ ->
    context.model.extraStore[BottomAxisLabelKey][x.toInt()]
}

@Composable
fun VicoChartsBarContent(
    modifier: Modifier = Modifier,
    timeSpan: QuoteTimeSpan,
    averageItem: RollingAverage,
    state: ResultData<StatsDetail<Entry>>,
    refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is ResultData.Success -> {
                val modelProducer = remember { CartesianChartModelProducer() }
                CartesianChartHost(
                    modifier=Modifier.heightIn(min=400.dp),
                    chart =
                        rememberCartesianChart(
                            rememberColumnCartesianLayer(),
                            startAxis = VerticalAxis.rememberStart(
                                size = BaseAxis.Size.Fixed(100.dp)
                            ),
                            bottomAxis = HorizontalAxis.rememberBottom(
                                itemPlacer = remember { HorizontalAxis.ItemPlacer.segmented() },
                                valueFormatter = BottomAxisValueFormatter,
                                size = BaseAxis.Size.Fixed(80.dp)
                            ),
                        ),
                    modelProducer = modelProducer,
                )
                LaunchedEffect(Unit) {
                    modelProducer.runTransaction {
                        columnSeries {
                            val transactionsEntries = state.data.transactionsEntries
                            val values = mutableListOf<Float>()
                            val labels = mutableListOf<String>()
                            transactionsEntries.forEach {
                                val time = it.x.format("HH:mm:ss")
                                values.add(it.y)
                                labels.add(time)
                            }
                            series(values)
                            extras { it[BottomAxisLabelKey] = labels }
                        }
                    }
                }
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
