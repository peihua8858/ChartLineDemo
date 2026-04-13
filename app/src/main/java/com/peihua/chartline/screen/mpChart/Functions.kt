package com.peihua.chartline.screen.mpChart

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class MPFunctions(override val nickName: String, override val value: String,override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.LineChart.value),
    BAR_CHART("Bar Chart", LineChartRoute.BarChart.value),
    PIE_CHART("Pie Chart", LineChartRoute.PieChart.value)
}