package com.peihua.chartline.screen.charty

import android.content.Context
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.Entry
import com.himanshoe.charty.combo.ComboChart
import com.himanshoe.charty.combo.data.ComboChartData
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.format

@Composable
fun ChartyChartsComboContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    ComboChart(
        modifier = modifier.heightIn(min = 400.dp),
        data = {
            data.chartyChartModelComboData(context)
        })

}

private fun StatsDetail<Entry>.chartyChartModelComboData(context: Context): List<ComboChartData> {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<ComboChartData>()
    transactionsEntries.forEach {
        val time = it.x.format("HH:mm:ss")
        values.add(ComboChartData(time, it.y, it.y))
    }
    return values
}