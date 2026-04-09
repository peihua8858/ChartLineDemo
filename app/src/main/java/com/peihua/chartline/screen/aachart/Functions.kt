package com.peihua.chartline.screen.aachart

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class AAChartFunctions(override val nickName: String, override val value: String) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.AALineChart.value),
    BAR_CHART("BarChart", LineChartRoute.AABarChart.value)
}