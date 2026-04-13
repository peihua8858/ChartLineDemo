package com.peihua.chartline

sealed class LineChartRoute(val value: String) {
    object Splash : LineChartRoute("Screen")
    object Main : LineChartRoute("Main")
    object Quote : LineChartRoute("Quote")
    object MpChart : LineChartRoute("MpChart")
    object LineChart : LineChartRoute("LineChart")
    object BarChart: LineChartRoute("BarChart")
    object PieChart: LineChartRoute("PieChart")
    object AAChartCore: LineChartRoute("AAChartCore")
    object AALineChart: LineChartRoute("AALineChart")
    object AAColumnChart: LineChartRoute("AAColumnChart")
    object AABarChart: LineChartRoute("AABarChart")
    object AnyChart: LineChartRoute("AnyChart")
    object AnyChartLine: LineChartRoute("AnyChartLine")
    object AnyChartColumn: LineChartRoute("AnyChartColumn")
    object AnyChartBar: LineChartRoute("AnyChartBar")
}