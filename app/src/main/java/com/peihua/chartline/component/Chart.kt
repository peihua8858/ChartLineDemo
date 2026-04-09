package com.peihua.chartline.component

import android.graphics.Color
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AAOptions
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import com.peihua.chartline.screen.mpChart.DayAxisValueFormatter
import com.peihua.chartline.screen.mpChart.MyAxisValueFormatter
import com.peihua8858.compose.tools.isSystemDarkMode
import com.peihua8858.compose.tools.rememberDerivedStateOf

@Composable
fun LineChart(modifier: Modifier = Modifier, data: LineData) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .heightIn(min = 300.dp), factory = {
            LineChart(it).apply {
                description.isEnabled = false
                isDragEnabled = false
                xAxis.isEnabled = false
                axisLeft.setDrawAxisLine(true)
                axisLeft.textColor = if (it.isSystemDarkMode) Color.WHITE else Color.BLACK
                axisRight.isEnabled = false
                legend.isEnabled = true
                setDrawMarkers(true)
                setScaleXEnabled(false)
                setScaleYEnabled(false)
                setScaleEnabled(false);
                setTouchEnabled(true)
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
                val xAxisFormatter = DayAxisValueFormatter(this);

                val xAxis = getXAxis();
                xAxis.setPosition(XAxisPosition.BOTTOM);
//                        xAxis.setTypeface(tfLight);
                xAxis.setDrawGridLines(false);
                xAxis.setGranularity(1f); // only intervals of 1 day
                xAxis.setLabelCount(7);
                xAxis.setValueFormatter(xAxisFormatter);
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
fun AAChartView(modifier: Modifier = Modifier, data: AAChartModel) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            AAChartView(it).apply {
                layoutParams = android.view.ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { view ->
            // Draw once the view has a size; avoids blank screen on first compose.
            view.post {
                view.aa_drawChartWithChartModel(data)
            }
        }
    )
}

@Composable
fun AAChartView(modifier: Modifier = Modifier, options: AAOptions, fetchData: (AAChartView?) -> Array<AASeriesElement>) {
    var chartViewRef by remember { mutableStateOf<AAChartView?>(null) }
    val data by rememberDerivedStateOf { fetchData(chartViewRef) }
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { ctx ->
            AAChartView(ctx).apply {
                chartViewRef = this
                layoutParams = android.view.ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { view ->
            // Draw once the view has a size; avoids blank screen on first compose.
            view.post {
                view.aa_refreshChartWithChartOptions(options)
                view.aa_onlyRefreshTheChartDataWithChartOptionsSeriesArray(data)
            }
        }
    )
}