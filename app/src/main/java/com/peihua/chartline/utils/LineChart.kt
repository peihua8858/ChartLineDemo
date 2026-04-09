package com.peihua.chartline.utils

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.Fill
import com.peihua.chartline.R
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


fun StatsDetail<Entry>.lineDataSet(context: Context): LineDataSet =
    LineDataSet(this.transactionsEntries, "transactions_per_second").apply {
        mode = LineDataSet.Mode.CUBIC_BEZIER
        color = (if (context.isSystemDarkMode) Purple500 else Purple200).toArgb()
        highLightColor = Purple700.toArgb()
        fillAlpha = 20
        lineWidth = 1F
        setDrawFilled(false)
        setDrawCircles(false)
    }


fun StatsDetail<BarEntry>.barDataSet(context: Context): BarDataSet {
//    val values = ArrayList<BarEntry>()
//    val start = 1f
//    var i = start.toInt()
//    while (i < start + 12) {
//        val value = (Math.random() * (50 + 1)).toFloat()
//
//        if (Math.random() * 100 < 25) {
//            values.add(BarEntry(i.toFloat(), value, ContextCompat.getDrawable(context, R.drawable.star)));
//        } else {
//            values.add(BarEntry(i.toFloat(), value))
//        }
//        i++
//    }
//    Log.d("BarChart", "transactionsEntries: ${this.transactionsEntries.joinToString(",")}")
    return BarDataSet(transactionsEntries, "transactions_per_second").apply {
        Log.d("BarChart", "barDataSet: ${this.values.size}")
        Log.d("BarChart", "barDataSet: ${this.values.joinToString(",")}")
        setDrawValues(false)
        setValueTextSize(10f);
        setDrawIcons(true);
//        setValueTypeface(tfLight);
        val startColor1 = ContextCompat.getColor(context, android.R.color.holo_orange_light)
        val startColor2 = ContextCompat.getColor(context, android.R.color.holo_blue_light)
        val startColor3 = ContextCompat.getColor(context, android.R.color.holo_orange_light)
        val startColor4 = ContextCompat.getColor(context, android.R.color.holo_green_light)
        val startColor5 = ContextCompat.getColor(context, android.R.color.holo_red_light)
        val endColor1 = ContextCompat.getColor(context, android.R.color.holo_blue_dark)
        val endColor2 = ContextCompat.getColor(context, android.R.color.holo_purple)
        val endColor3 = ContextCompat.getColor(context, android.R.color.holo_green_dark)
        val endColor4 = ContextCompat.getColor(context, android.R.color.holo_red_dark)
        val endColor5 = ContextCompat.getColor(context, android.R.color.holo_orange_dark)

        val gradientFills: MutableList<Fill> = ArrayList<Fill>()
        gradientFills.add(Fill(startColor1, endColor1))
        gradientFills.add(Fill(startColor2, endColor2))
        gradientFills.add(Fill(startColor3, endColor3))
        gradientFills.add(Fill(startColor4, endColor4))
        gradientFills.add(Fill(startColor5, endColor5))

        setFills(gradientFills)

        color = (if (context.isSystemDarkMode) Purple500 else Purple200).toArgb()
        highLightColor = Purple700.toArgb()
    }
}
