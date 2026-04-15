package com.peihua.chartline.screen.aaychart

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class AAYChartFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.AAYChartLine.value),
    BAR_CHART("Bar Chart", LineChartRoute.AAYChartBar.value),
    COLUMN_CHART("Column Chart", LineChartRoute.AAYChartColumn.value),
//    RADAR_CHART("Radar Chart", LineChartRoute.AAYChartRadar.value),
    COMBO_CHART("Combo Chart", LineChartRoute.AAYChartCombo.value)
}