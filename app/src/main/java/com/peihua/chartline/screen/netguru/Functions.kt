package com.peihua.chartline.screen.netguru

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class NetguruChartFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.NetguruChartLine.value),
    BAR_CHART("Bar Chart", LineChartRoute.NetguruChartBar.value),
    COLUMN_CHART("Column Chart", LineChartRoute.NetguruChartColumn.value),
    RADAR_CHART("Radar Chart", LineChartRoute.NetguruChartRadar.value),
    COMBO_CHART("Combo Chart", LineChartRoute.NetguruChartCombo.value)
}