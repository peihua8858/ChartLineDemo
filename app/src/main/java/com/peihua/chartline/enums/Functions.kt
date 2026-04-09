package com.peihua.chartline.enums

import com.peihua.chartline.LineChartRoute

interface  Functions {
    val nickName: String
    val value: String
}

enum class MainFunctions(override val nickName: String, override val value: String) : Functions {
    AP_CHART("MP Android Chart", LineChartRoute.MpChart.value),
    AAChartCore("AAChartCore", LineChartRoute.AAChartCore.value)
}