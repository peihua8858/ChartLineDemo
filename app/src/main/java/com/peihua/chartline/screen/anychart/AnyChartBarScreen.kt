package com.peihua.chartline.screen.anychart

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.core.Chart
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.component.AnyChartView
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import com.peihua8858.tools.utils.dLog

@Composable
fun AnyChartBarContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    AnyChartView(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        data.anyCharModelBarData(context)
    }
}

private fun StatsDetail<Entry>.anyCharModelBarData(context: android.content.Context): Chart {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<DataEntry>()
    transactionsEntries.forEach {
        val time = it.x.format("HH:mm:ss")
        values.add(ValueDataEntry(time, it.y))
    }
    dLog { "anyCharModelLineData>>>values: ${values.joinToString { it.generateJs() }}" }
    val cartesian = AnyChart.bar()
    val set = Set.instantiate()
    set.data(values)
    val series1Mapping = set.mapAs("{ x: 'x', value: 'value' }")
    val series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
    val series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

    val series1 = cartesian.bar(series1Mapping)
    series1.name("Brandy")
    series1.hovered().markers().enabled(true)
    series1.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4.0)
    series1.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5.0)
        .offsetY(5.0)

    val series2 = cartesian.bar(series2Mapping)
    series2.name("Whiskey")
    series2.hovered().markers().enabled(true)
    series2.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4.0)
    series2.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5.0)
        .offsetY(5.0)

    val series3 = cartesian.bar(series3Mapping)
    series3.name("Tequila")
    series3.hovered().markers().enabled(true)
    series3.hovered().markers()
        .type(MarkerType.CIRCLE)
        .size(4.0)
    series3.tooltip()
        .position("right")
        .anchor(Anchor.LEFT_CENTER)
        .offsetX(5.0)
        .offsetY(5.0)
    return cartesian.apply {
        legend().enabled(true)

    }
}
