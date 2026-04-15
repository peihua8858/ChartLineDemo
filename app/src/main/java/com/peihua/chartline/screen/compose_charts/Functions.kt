package com.peihua.chartline.screen.compose_charts

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class ComposeChartsFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.ComposeChartsLine.value),
    BAR_CHART("Bar Chart", LineChartRoute.ComposeChartsBar.value),
    COLUMN_CHART("Column Chart", LineChartRoute.ComposeChartsColumn.value),
//    RADAR_CHART("Radar Chart", LineChartRoute.ComposeChartsRadar.value),
    COMBO_CHART("Combo Chart", LineChartRoute.ComposeChartsCombo.value)
}