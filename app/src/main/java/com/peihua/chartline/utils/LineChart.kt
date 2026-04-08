package com.peihua.chartline.utils

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.ui.theme.Purple200
import com.peihua.chartline.ui.theme.Purple500
import com.peihua.chartline.ui.theme.Purple700
import com.peihua8858.compose.tools.isSystemDarkMode

private const val DEFAULT_ANIMATE_XY_DURATION = 300
private const val MIN_ENTRY_COUNT_FOR_ANIMATION = 30

fun LineChart.setLineDataSet(lineDataSet: LineDataSet? = null, animateXDuration: Int = 0) {
    lineDataSet?.let {
        clear()
        data = LineData(it).apply { setDrawValues(false) }
    }

    if (lineDataSet?.entryCount.orZero() > MIN_ENTRY_COUNT_FOR_ANIMATION) {
        animateX(if (animateXDuration > 0) animateXDuration else DEFAULT_ANIMATE_XY_DURATION)
    }
}


fun StatsDetail.lineDataSet(context: Context): LineDataSet =
    LineDataSet(this.transactionsEntries, "transactions_per_second").apply {
        mode = LineDataSet.Mode.CUBIC_BEZIER
        color =(if(context.isSystemDarkMode) Purple500 else Purple200).toArgb()
        highLightColor = Purple700.toArgb()
        fillAlpha = 20
        lineWidth = 1F
        setDrawFilled(false)
        setDrawCircles(false)
    }


//fun StatsDetail.barDataSet(context: Context): BarDataSet =
//    BarDataSet(this.transactionsEntries, "transactions_per_second").apply {
//        mode = LineDataSet.Mode.CUBIC_BEZIER
//        color =(if(context.isSystemDarkMode) Purple500 else Purple200).toArgb()
//        highLightColor = Purple700.toArgb()
//        fillAlpha = 20
//        lineWidth = 1F
//        setDrawFilled(false)
//        setDrawCircles(false)
//    }
