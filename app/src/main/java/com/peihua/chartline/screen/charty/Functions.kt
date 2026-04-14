package com.peihua.chartline.screen.charty

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class ChartyChartsFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.ChartyLine.value),
    COLUMN_CHART("Column Chart", LineChartRoute.ChartyColumn.value),
//    RADAR_CHART("Radar Chart", LineChartRoute.VicoChartsRadar.value),
    COMBO_CHART("Combo Chart", LineChartRoute.ChartyCombo.value)
}