package com.peihua.chartline.screen.aachart

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartAnimationType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAOptions
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAChart
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.component.AAChartView
import com.peihua.chartline.model.StatsDetail

@Composable
fun AALineChartContent(
    modifier: Modifier = Modifier,
    data: StatsDetail<Entry>,
) {
    val context = LocalContext.current
    AAChartView(
        modifier = modifier
            .fillMaxWidth(),
        data = data.aaCharModelLineData(context),
    )
}

private fun configureChartOptions(): AAOptions {
    return AAOptions()
        .chart(
            AAChart()
                .type(AAChartType.Line)
                .animation(AAChartAnimationType.EaseInOutQuart)
                .inverted(false)
        )
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