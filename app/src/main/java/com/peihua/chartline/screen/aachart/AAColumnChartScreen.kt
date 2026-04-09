package com.peihua.chartline.screen.aachart

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartAnimationType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartStackingType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartSymbolType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartZoomType
import com.github.aachartmodel.aainfographics.aachartcreator.AAOptions
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAChart
import com.github.mikephil.charting.data.BarEntry
import com.peihua.chartline.component.AAChartView
import com.peihua.chartline.component.CustomSlider
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.component.Toolbar
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.viewmodel.BarChartStatsViewModel
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.model.isStarting
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AAColumnChartScreen(navController: NavController, viewModel: BarChartStatsViewModel = hiltViewModel()) {
    val state = viewModel.barState.value
    val refresh = { count: Int, range: Int ->
        if (!state.isStarting()) {
            viewModel.fetchStatsDetail(count, range)
        }
    }
    Toolbar(
        title = "AA Android Column Chart",
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray),
        navigateUp = { navController.popBackStack() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.padding(start = 32.dp, top = 16.dp, end = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Time span:")
                CustomSlider(
                    modifier = Modifier
                        .padding(8.dp),
                    value = viewModel.timeSpan.toFloat(),
                    thumbText = { it.toInt().toString() },
                    valueRange = 0f..100f
                ) {
                    refresh(it.toInt(), viewModel.averageItem)
                }
            }
            Row(
                modifier = Modifier.padding(start = 32.dp, top = 16.dp, end = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Rolling avg:")
                CustomSlider(
                    modifier = Modifier
                        .padding(8.dp),
                    value = viewModel.averageItem.toFloat(),
                    thumbText = { it.toInt().toString() },
                    valueRange = 0f..100f
                ) {
                    refresh(viewModel.timeSpan, it.toInt())
                }
            }
            AAColumnChartContent(
                modifier = Modifier,
                count = viewModel.timeSpan,
                range = viewModel.averageItem,
                state = state,
                refresh = refresh
            )
        }

    }
}

@Composable
private fun AAColumnChartContent(
    modifier: Modifier = Modifier,
    count: Int,
    range: Int,
    state: ResultData<StatsDetail<BarEntry>>,
    refresh: (count: Int, range: Int) -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is ResultData.Success -> {
                AAChartView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    data = state.data.aaCharModelLineData(context),
                )
            }

            is ResultData.Failure -> {
                ErrorView(
                    retry = {
                        refresh(count, range)
                    })
            }

            is ResultData.Initialize -> {
                refresh(count, range)
            }

            is ResultData.Starting -> {
                LoadingView(modifier = Modifier)
            }
        }
    }

}

private fun configureBasicOptions(): AAChartModel {
    return AAChartModel()
        .backgroundColor("#4b2b7f")
        .dataLabelsEnabled(false)
        .yAxisGridLineWidth(0)
        .touchEventEnabled(true).apply {
//            aa_toAAOptions().plotOptions?.bar
//                ?.groupPadding(0f)
//                ?.pointPadding(0f)
//                ?.borderRadius(5f)
        }

}

private fun StatsDetail<BarEntry>.aaCharModelLineData(context: Context): AAChartModel {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Float>()
    transactionsEntries.forEach {
        values.add(it.x*it.y)
    }
    return configureBasicOptions()
        .backgroundColor("#FFFFFF")
        .chartType(AAChartType.Column)
        .zoomType(AAChartZoomType.XY)
        .inverted(false)
        .stacking(AAChartStackingType.Normal)
        .animationType(AAChartAnimationType.EaseInOutQuart)
//        .categories(arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun"))
//        .markerRadius(5)
        .markerSymbol(AAChartSymbolType.Circle)
//        .yAxisVisible(true)
//        .yAxisGridLineWidth(1)
        .series(arrayOf(AASeriesElement().name("Demo Line").data(values.toTypedArray())))
}
private fun configureChartOptions(): AAOptions {
    return AAOptions()
        .chart(AAChart()
            .type(AAChartType.Bar)
            .animation(AAChartAnimationType.EaseInOutQuart)
            .inverted(false))
}
private fun StatsDetail<BarEntry>.chartDataSeriesArray(): Array<AASeriesElement> {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Float>()
    transactionsEntries.forEach {
        values.add(it.y)
    }
    return arrayOf(AASeriesElement().name("Demo Line").data(values.toTypedArray()))

}
private fun configureChartSeriesArray(): Array<AASeriesElement> {
    val maxRange = 40
    val numberArr1 = arrayOfNulls<Any>(maxRange)
    val numberArr2 = arrayOfNulls<Any>(maxRange)

    val random = (Math.random() * 37 + 1).toInt()

    for (i in 0 until maxRange) {
        numberArr1[i] = sin(random * (i * Math.PI / 180)) + i * 2 * 0.01
        numberArr2[i] = cos(random * (i * Math.PI / 180)) + i * 3 * 0.01
    }

    return arrayOf(
        AASeriesElement().name("2017").data(numberArr1 as Array<Any>),
        AASeriesElement().name("2018").data(numberArr2 as Array<Any>),
        AASeriesElement().name("2019").data(numberArr1 as Array<Any>),
        AASeriesElement().name("2020").data(numberArr2 as Array<Any>)
    )
}