package com.peihua.chartline.enums

import com.peihua.chartline.LineChartRoute
import com.peihua.chartline.R

interface Functions {
    val nickName: String
    val value: String
    val drawableId: Int
        get() = 0
    val description: String
        get() = ""
    val isVisible: Boolean
        get() = true
}

enum class MainFunctions(
    override val nickName: String,
    override val value: String,
    override val drawableId: Int = 0,
    override val description: String = "",
    override val isVisible: Boolean = true
) : Functions {
    AP_CHART(
        "MP Android Chart",
        LineChartRoute.MpChart.value,
        R.mipmap.ic_mp_chart,
        "基于Android原生Canvas绘制的安卓图表视图库，支持折线图-条形图-饼图-雷达图-气泡图和蜡烛图，以及缩放、平移和动画。"
    ),
    AAChartCore(
        "AA Chart Core",
        LineChartRoute.AAChartCore.value,
        R.mipmap.ic_launcher_round,
        "基于WebView JavaScript的图表库，支持折线图-条形图-饼图-雷达图-气泡图和蜡烛图。"
    ),
    ANY_CHART(
        "Any Chart",
        LineChartRoute.AnyChart.value,
        R.mipmap.ic_any_chart,
        "基于WebViewJavaScript的图表库，支持折线图-条形图-饼图-雷达图-气泡图和蜡烛图。"
    ),
    HD_CHART(
        "HD Chart",
        LineChartRoute.HdCharts.value,
        R.mipmap.ic_hd_charts,
        "基于Jetpack Compose 多平台（Android · iOS ·Web ·桌面版）图表库，支持折线图-条形图-饼图-雷达图-气泡图和蜡烛图。"
    ),
    VICO_CHART(
        "Vico Chart",
        LineChartRoute.VicoCharts.value,
        R.mipmap.ic_vico_chart,
        "基于Jetpack Compose 多平台（Android · iOS）图表库，支持折线图-条形图-饼图和蜡烛图。"
    ),
    CHARTY(
        "Charty",
        LineChartRoute.Charty.value,
        R.mipmap.ic_launcher_round,
        "基于Jetpack Compose 多平台（Android · iOS）图表库，支持折线图-条形图-饼图-雷达图-气泡图和蜡烛图。"
    ),
    Y_CHARTS(
        "Y Charts",
        LineChartRoute.YCharts.value,
        R.mipmap.ic_launcher_round,
        "基于Jetpack Compose Android平台图表库，支持折线图-条形图-饼图-雷达图-气泡图和蜡烛图。"
    ),
    COMPOSE_CHARTS(
        "Compose Charts",
        LineChartRoute.ComposeCharts.value,
        R.mipmap.ic_launcher_round,
        "基于Jetpack Compose 多平台（Android · iOS ·Web ·桌面版）图表库，支持折线图-条形图-饼图。",
        false
    ),
    AAY_CHART(
        "AAY-chart",
        LineChartRoute.AAYChart.value,
        R.mipmap.ic_launcher_round,
        "基于Jetpack Compose 多平台（Android · iOS）图表库，支持折线图-条形图-饼图。",
        false
    ),
}