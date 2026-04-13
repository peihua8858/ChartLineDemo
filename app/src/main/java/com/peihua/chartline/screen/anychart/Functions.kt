package com.peihua.chartline.screen.anychart

import com.anychart.chart.common.dataentry.DataEntry
import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.enums.Functions

enum class AnyChartFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    LINE_CHART("Line Chart", LineChartRoute.AnyChartLine.value),
    COLUMN_CHART("Column Chart", LineChartRoute.AnyChartColumn.value),
    BAR_CHART("Bar Chart", LineChartRoute.AnyChartBar.value)
}

class ValueDataEntry : DataEntry {
    constructor(x: String, value: Float) {
        setValue("x", x)
        setValue("value", value)
        setValue("value2", value-0.1);
        setValue("value3", value-0.2);
    }

    constructor(x: Number?, value: Number?) {
        setValue("x", x)
        setValue("value", value)
    }

    override fun toString(): String {
        return ""
    }

}