package com.peihua.chartline.screen.netguru

import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.BaseAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.compose.cartesian.data.columnSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.data.ExtraStore
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format

private val BottomAxisLabelKey = ExtraStore.Key<List<String>>()
private val BottomAxisValueFormatter = CartesianValueFormatter { context, x, _ ->
    context.model.extraStore[BottomAxisLabelKey][x.toInt()]
}

@Composable
fun NetguruChartRadarContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val modelProducer = remember { CartesianChartModelProducer() }
    CartesianChartHost(
        modifier = modifier.heightIn(min = 400.dp),
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
                val transactionsEntries = data.transactionsEntries
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

