package com.peihua.chartline.screen.vicocharts

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class VicoChartsFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.VicoChartsLine.value),
    COLUMN_CHART("Column Chart", LineChartRoute.VicoChartsColumn.value),
//    RADAR_CHART("Radar Chart", LineChartRoute.VicoChartsRadar.value),
    COMBO_CHART("Combo Chart", LineChartRoute.VicoChartsCombo.value)
}