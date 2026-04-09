package com.peihua.chartline

sealed class LineChartRoute(val value: String) {
    object Splash : LineChartRoute("Screen")
    object Main : LineChartRoute("Main")
    object Quote : LineChartRoute("Quote")
    object MpChart : LineChartRoute("MpChart")
    object LineChart : LineChartRoute("LineChart")
    object BarChart: LineChartRoute("BarChart")
    object AAChartCore: LineChartRoute("AAChartCore")
    object AALineChart: LineChartRoute("AALineChart")
    object AABarChart: LineChartRoute("AABarChart")
}