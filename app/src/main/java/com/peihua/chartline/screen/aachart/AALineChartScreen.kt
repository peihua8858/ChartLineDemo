package com.peihua.chartline.screen.aachart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartAnimationType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAOptions
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAChart
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.component.AAChartView
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua8858.tools.model.ResultData

@Composable
 fun AALineChartContent(
    modifier: Modifier = Modifier,
    timeSpan: QuoteTimeSpan,
    averageItem: RollingAverage,
    state: ResultData<StatsDetail<Entry>>,
    refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
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
                        refresh(timeSpan, averageItem)
                    })
            }

            is ResultData.Initialize -> {
                refresh(timeSpan, averageItem)
            }

            is ResultData.Starting -> {
                LoadingView(modifier = Modifier)
            }
        }
    }

}
private fun configureChartOptions(): AAOptions {
    return AAOptions()
        .chart(AAChart()
            .type(AAChartType.Line)
            .animation(AAChartAnimationType.EaseInOutQuart)
            .inverted(false))
}
private fun StatsDetail<Entry>.chartDataSeriesArray(): Array<AASeriesElement> {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Float>()
    transactionsEntries.forEach {
        values.add(it.y)
    }
    return arrayOf(AASeriesElement().name("Demo Line").data(values.toTypedArray()))

}
private fun configureBasicOptions(): AAChartModel {
    return AAChartModel()
        .backgroundColor("#4b2b7f")
        .dataLabelsEnabled(false)
        .yAxisGridLineWidth(0)
        .touchEventEnabled(true)
}
private fun StatsDetail<Entry>.aaCharModelLineData(context: android.content.Context): AAChartModel {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<Float>()
    transactionsEntries.forEach {
        values.add(it.y)
    }
    return configureBasicOptions()
        .backgroundColor("#FFFFFF")
        .chartType(AAChartType.Line)
//        .categories(arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun"))
        .markerRadius(5)
//        .markerSymbol(AAChartSymbolType.Circle)
        .yAxisVisible(true)
        .yAxisGridLineWidth(1)
        .animationType(AAChartAnimationType.EaseInOutQuart)
        .series(arrayOf(AASeriesElement().name("Demo Line").data(values.toTypedArray())))
}