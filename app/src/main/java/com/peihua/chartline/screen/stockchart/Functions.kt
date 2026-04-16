package com.peihua.chartline.screen.stockchart

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class StockChartFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("k Line Chart", LineChartRoute.StockChartLine.value),
//    BAR_CHART("Bar Chart", LineChartRoute.StockChartBar.value),
//    COLUMN_CHART("Column Chart", LineChartRoute.StockChartColumn.value),
//    RADAR_CHART("Radar Chart", LineChartRoute.StockChartRadar.value),
//    COMBO_CHART("Combo Chart", LineChartRoute.StockChartCombo.value)
}