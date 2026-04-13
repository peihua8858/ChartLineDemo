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
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.PieData
import com.peihua.chartline.screen.mpChart.DayAxisValueFormatter
import com.peihua.chartline.screen.mpChart.MyAxisValueFormatter
import com.peihua8858.compose.tools.isSystemDarkMode
import com.peihua8858.tools.utils.dLog

@Composable
fun LineChart(modifier: Modifier = Modifier, data: LineData) {
    dLog { "LineChart>>>>>>dataSets.size:${data.dataSets.size}" }
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .heightIn(min = 400.dp), factory = {
            LineChart(it).apply {
                description.isEnabled = false
                isDragEnabled = true
                xAxis.isEnabled = true
                axisRight.isEnabled = false
                legend.isEnabled = true
                val leftAxis: YAxis = axisLeft
                leftAxis.setDrawAxisLine(true)
                leftAxis.setDrawZeroLine(true)
                leftAxis.setDrawGridLines(true)
                leftAxis.isEnabled = true
                leftAxis.textColor = if (it.isSystemDarkMode) Color.WHITE else Color.BLACK
                leftAxis.setDrawTopYLabelEntry(true)
                setDrawMarkers(true)
                setScaleXEnabled(false)
                setScaleYEnabled(false)
                setScaleEnabled(false)
                setTouchEnabled(true)
                xAxis.setDrawLabels(true)
                xAxis.setDrawGridLines(false)
                xAxis.setCenterAxisLabels(true)
                xAxis.position = XAxisPosition.BOTTOM
                this.setClipValuesToContent(true)
                setPinchZoom(false);
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
                xAxis.isEnabled = true
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
                val xAxisFormatter = DayAxisValueFormatter(this);

                val xAxis = getXAxis();
                xAxis.setPosition(XAxisPosition.BOTTOM);
//                        xAxis.setTypeface(tfLight);
                xAxis.setDrawGridLines(false);
                xAxis.setGranularity(1f); // only intervals of 1 day
                xAxis.setLabelCount(7);
                xAxis.setValueFormatter(xAxisFormatter);
                xAxis.setDrawLabels(true)
                //
                val custom = MyAxisValueFormatter();

                val leftAxis = getAxisLeft();
//                        leftAxis.setTypeface(tfLight);
                leftAxis.setLabelCount(8, false);
                leftAxis.setValueFormatter(custom);
                leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
                leftAxis.setSpaceTop(15f);
                leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
                invalidate()
                this.data = data
            }
        })
}


@Composable
fun PieChart(modifier: Modifier = Modifier, centerText: CharSequence = "", data: PieData) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .heightIn(min = 300.dp), factory = {
            PieChart(it).apply {
                description.isEnabled = false
                legend.isEnabled = true
                setDrawMarkers(true)
                setTouchEnabled(true)
                setCenterText(centerText);
                invalidate()
                this.data = data
            }
        })
}