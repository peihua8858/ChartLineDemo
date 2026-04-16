package com.peihua.chartline.screen.vicocharts

import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.lineSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.data.ExtraStore
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format

private val BottomAxisLabelKey = ExtraStore.Key<List<String>>()

@Composable
fun VicoChartsLineContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val modelProducer = remember { CartesianChartModelProducer() }
    CartesianChartHost(
        modifier = modifier.heightIn(min = 400.dp),
        chart =
            rememberCartesianChart(
                rememberLineCartesianLayer(),
                startAxis = VerticalAxis.rememberStart(),
                bottomAxis = HorizontalAxis.rememberBottom(),
            ),
        modelProducer = modelProducer,
    )
    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            lineSeries {
                val transactionsEntries = data.transactionsEntries
                val values = mutableListOf<Float>()
                val labels = mutableListOf<String>()
                transactionsEntries.forEach {
                    val time = it.x.format("HH:mm:ss")
                    values.add(it.y)
                    labels.add(time)
                }
                series(*values.toTypedArray())
                extras { it[BottomAxisLabelKey] = labels }
            }
        }
    }
}