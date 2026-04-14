package com.peihua.chartline.screen.hdcharts

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class HdChartsFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.HdChartsLine.value),
    COLUMN_CHART("Column Chart", LineChartRoute.HdChartsColumn.value),
    BAR_CHART("Bar Chart", LineChartRoute.HdChartsBar.value),
    RADAR_CHART("Radar Chart", LineChartRoute.HdChartsRadar.value)
}