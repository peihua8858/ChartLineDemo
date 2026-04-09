package com.peihua.chartline.screen.aachart

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class AAChartFunctions(override val nickName: String, override val value: String) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.AALineChart.value),
    COLUMN_CHART("Column Chart", LineChartRoute.AAColumnChart.value),
    BAR_CHART("Bar Chart", LineChartRoute.AABarChart.value)
}