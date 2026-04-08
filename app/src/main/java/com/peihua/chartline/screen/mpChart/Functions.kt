package com.peihua.chartline.screen.mpChart

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class MPFunctions(override val nickName: String, override val value: String) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.LineChart.value),
    BAR_CHART("BarChart", LineChartRoute.BarChart.value)
}