package com.peihua.chartline.enums

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.R

interface Functions {
    val nickName: String
    val value: String
    val drawableId: Int
        get() = 0
}

enum class MainFunctions(override val nickName: String, override val value: String, override val drawableId: Int = 0) : Functions {
    AP_CHART("MP Android Chart", LineChartRoute.MpChart.value, R.mipmap.mp_chart_icon),
    AAChartCore("AA Chart Core", LineChartRoute.AAChartCore.value, R.mipmap.ic_launcher_round),
    ANY_CHART("Any Chart", LineChartRoute.AnyChart.value, R.mipmap.ic_any_chart),
    HD_CHART("HD Chart", LineChartRoute.HdCharts.value, R.mipmap.ic_hd_charts),
    VICO_CHART("Vico Chart", LineChartRoute.VicoCharts.value, R.mipmap.ic_vico_chart),
    CHARTY("Charty", LineChartRoute.Charty.value, R.mipmap.ic_launcher_round),
    Y_CHARTS("Y Charts", LineChartRoute.YCharts.value, R.mipmap.ic_launcher_round)
}