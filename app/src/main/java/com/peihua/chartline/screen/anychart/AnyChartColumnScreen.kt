package com.peihua.chartline.screen.anychart

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.core.Chart
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.component.AnyChartView
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format
import com.peihua8858.tools.utils.dLog

@Composable
fun AnyChartColumnContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    AnyChartView(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        data.anyCharModelColumnData()
    }
}

private fun StatsDetail<Entry>.anyCharModelColumnData(): Chart {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<DataEntry>()
    transactionsEntries.forEach {
        val time = it.x.format("HH:mm:ss")
        values.add(ValueDataEntry(time, it.y))
    }
    dLog { "anyCharModelLineData>>>values: ${values.joinToString { it.generateJs() }}" }
    val cartesian = AnyChart.column()
    val column = cartesian.column(values)
    column.tooltip()
        .titleFormat("{%X}")
        .position(Position.CENTER_BOTTOM)
        .anchor(Anchor.CENTER_BOTTOM)
        .offsetX(0f)
        .offsetY(5f)
        .format("\${%Value}{groupsSeparator: }")
    cartesian.animation(true)
    cartesian.title("Top 10 Cosmetic Products by Revenue")

    cartesian.yScale().minimum(0.0)

    cartesian.yAxis(0).labels().format("\${%Value}{groupsSeparator: }")

    cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
    cartesian.interactivity().hoverMode(HoverMode.BY_X)

    cartesian.xAxis(0).title("Product")
    cartesian.yAxis(0).title("Revenue")
    return cartesian.apply {
        legend().enabled(true)

    }
}