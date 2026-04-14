package com.peihua.chartline.screen.ycharts

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class YChartsFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.YChartsLine.value),
    BAR_CHART("Bar Chart", LineChartRoute.YChartsBar.value),
    COLUMN_CHART("Column Chart", LineChartRoute.YChartsColumn.value),
//    RADAR_CHART("Radar Chart", LineChartRoute.VicoChartsRadar.value),
    COMBO_CHART("Combo Chart", LineChartRoute.YChartsCombo.value)
}