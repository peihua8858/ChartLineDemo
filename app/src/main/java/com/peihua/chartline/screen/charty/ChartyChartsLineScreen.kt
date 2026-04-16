package com.peihua.chartline.screen.charty

import android.content.Context
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.data.LineData
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format

@Composable
fun ChartyChartsLineContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    LineChart(
        modifier = modifier.heightIn(min = 400.dp),
        data = {
            data.chartyChartModelLineData(context)
        })

}

private fun StatsDetail<Entry>.chartyChartModelLineData(context: Context): List<LineData> {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<LineData>()
    transactionsEntries.forEach {
        val time = it.x.format("HH:mm:ss")
        values.add(LineData(time, it.y))
    }
    return values
}