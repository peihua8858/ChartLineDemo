package com.peihua.chartline.screen.mpChart

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.peihua.chartline.component.LineChart
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.lineDataSet

@Composable
fun LineChartContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    LineChart(
        modifier = modifier
            .fillMaxWidth(),
        data = LineData(data.lineDataSet(context)).apply {
            setDrawValues(false)
        }
    )
}