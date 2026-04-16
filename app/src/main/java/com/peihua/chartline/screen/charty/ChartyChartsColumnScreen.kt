package com.peihua.chartline.screen.charty

import android.content.Context
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.himanshoe.charty.bar.BarChart
import com.himanshoe.charty.bar.data.BarData
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format

@Composable
fun ChartyChartsColumnContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    BarChart(
        modifier = modifier.heightIn(min = 400.dp),
        data = {
            data.chartyChartModelBarData(context)
        })

}

private fun StatsDetail<Entry>.chartyChartModelBarData(context: Context): List<BarData> {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<BarData>()
    transactionsEntries.forEach {
        val time = it.x.format("HH:mm:ss")
        values.add(BarData(time, it.y))
    }
    return values
}