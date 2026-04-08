package com.peihua.chartline.component

import android.graphics.Color
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import com.peihua8858.compose.tools.isSystemDarkMode

@Composable
fun LineChart(modifier: Modifier = Modifier, data: LineData) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .heightIn(min = 300.dp), factory = {
            LineChart(it).apply {
                description.isEnabled = false
                isDragEnabled = true
                xAxis.isEnabled = false
                axisLeft.setDrawAxisLine(true)
                axisLeft.textColor = if (it.isSystemDarkMode) Color.WHITE else Color.BLACK
                axisRight.isEnabled = false
                legend.isEnabled = true
                setDrawMarkers(true)
                setScaleXEnabled(true)
                setScaleYEnabled(true)
                setScaleEnabled(true);
                setTouchEnabled(true)
                setPinchZoom(true);
                setDrawGridBackground(false)
                setDrawBorders(false)
                invalidate()
                this.data = data
            }
        })
}

@Composable
fun BarChart(modifier: Modifier = Modifier, data: BarData) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .heightIn(min = 300.dp), factory = {
            BarChart(it).apply {
                description.isEnabled = false
                isDragEnabled = true
                xAxis.isEnabled = false
                axisLeft.setDrawAxisLine(true)
                axisLeft.textColor = if (it.isSystemDarkMode) Color.WHITE else Color.BLACK
                axisRight.isEnabled = false
                legend.isEnabled = true
                setDrawMarkers(true)
                setScaleXEnabled(true)
                setScaleYEnabled(true)
                setScaleEnabled(true);
                setTouchEnabled(true)
                setPinchZoom(true);
                setDrawGridBackground(false)
                setDrawBorders(false)
                setDrawBarShadow(false);
                setDrawValueAboveBar(true);
                setMaxVisibleValueCount(60)
                invalidate()
                this.data = data
            }
        })
}