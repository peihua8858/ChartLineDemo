package com.peihua.chartline

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peihua.chartline.enums.MainFunctions
import com.peihua.chartline.screen.BaseStatsDataScreen
import com.peihua.chartline.screen.FlowRowScreen
import com.peihua.chartline.screen.QuoteScreen
import com.peihua.chartline.screen.SplashScreen
import com.peihua.chartline.screen.aachart.AABarChartScreen
import com.peihua.chartline.screen.aachart.AAChartFunctions
import com.peihua.chartline.screen.aachart.AAColumnChartScreen
import com.peihua.chartline.screen.aachart.AALineChartContent
import com.peihua.chartline.screen.anychart.AnyChartBarContent
import com.peihua.chartline.screen.anychart.AnyChartColumnContent
import com.peihua.chartline.screen.anychart.AnyChartFunctions
import com.peihua.chartline.screen.anychart.AnyChartLineContent
import com.peihua.chartline.screen.charty.ChartyChartsBarContent
import com.peihua.chartline.screen.charty.ChartyChartsColumnContent
import com.peihua.chartline.screen.charty.ChartyChartsComboContent
import com.peihua.chartline.screen.charty.ChartyChartsFunctions
import com.peihua.chartline.screen.charty.ChartyChartsLineContent
import com.peihua.chartline.screen.charty.ChartyChartsRadarContent
import com.peihua.chartline.screen.hdcharts.HdChartsBarContent
import com.peihua.chartline.screen.hdcharts.HdChartsColumnContent
import com.peihua.chartline.screen.hdcharts.HdChartsFunctions
import com.peihua.chartline.screen.hdcharts.HdChartsLineContent
import com.peihua.chartline.screen.hdcharts.HdChartsRadarContent
import com.peihua.chartline.screen.mpChart.BarChartScreen
import com.peihua.chartline.screen.mpChart.LineChartContent
import com.peihua.chartline.screen.mpChart.MPFunctions
import com.peihua.chartline.screen.mpChart.PieChartScreen
import com.peihua.chartline.screen.vicocharts.VicoChartsColumnContent
import com.peihua.chartline.screen.vicocharts.VicoChartsComboContent
import com.peihua.chartline.screen.vicocharts.VicoChartsFunctions
import com.peihua.chartline.screen.vicocharts.VicoChartsLineContent
import com.peihua.chartline.screen.vicocharts.VicoChartsRadarContent
import com.peihua.chartline.screen.ycharts.YChartsBarContent
import com.peihua.chartline.screen.ycharts.YChartsColumnContent
import com.peihua.chartline.screen.ycharts.YChartsComboContent
import com.peihua.chartline.screen.ycharts.YChartsFunctions
import com.peihua.chartline.screen.ycharts.YChartsLineContent
import com.peihua8858.tools.utils.finish

@Composable
fun LineChartApp() {
    val navController = rememberNavController()
    val context = navController.context
//    BackHandler {
//    处理系统返回按钮
//        val startDestinationId = navController.graph.startDestinationId
//        val currentDestinationId = navController.currentDestination?.id
//
//        if (currentDestinationId == startDestinationId) {
//            context.finish()
//        } else {
//            navController.popBackStack()
//        }
//    }
    NavHost(navController = navController, startDestination = LineChartRoute.Splash.value) {
        composable(route = LineChartRoute.Splash.value) {
            SplashScreen(navController = navController)
        }
        composable(route = LineChartRoute.Main.value) {
            FlowRowScreen(navController, "Charts", MainFunctions.entries) {
                val popped = navController.popBackStack()
                if (!popped) {
                    context.finish()
                }
            }
        }
        composable(route = LineChartRoute.MpChart.value) {
            FlowRowScreen(navController, "MP Android Chart", MPFunctions.entries)
        }
        composable(route = LineChartRoute.Quote.value) {
            QuoteScreen(navController = navController)
        }
        composable(route = LineChartRoute.LineChart.value) {
            BaseStatsDataScreen(navController, title = "MP Android Line Chart") { modifier, timeSpan, averageItem, state, refresh ->
                LineChartContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.BarChart.value) {
            BarChartScreen(navController = navController)
        }
        composable(route = LineChartRoute.PieChart.value) {
            PieChartScreen(navController)
        }
        composable(route = LineChartRoute.AAChartCore.value) {
            FlowRowScreen(navController, "AA Chart Core", AAChartFunctions.entries)
        }
        composable(route = LineChartRoute.AALineChart.value) {
            BaseStatsDataScreen(navController, title = "AA Android Line Chart") { modifier, timeSpan, averageItem, state, refresh ->
                AALineChartContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.AAColumnChart.value) {
            AAColumnChartScreen(navController)
        }
        composable(route = LineChartRoute.AABarChart.value) {
            AABarChartScreen(navController)
        }
        composable(route = LineChartRoute.AnyChart.value) {
            FlowRowScreen(navController, "Any Charts", AnyChartFunctions.entries)
        }
        composable(route = LineChartRoute.AnyChartLine.value) {
            BaseStatsDataScreen(navController, title = "Any Android Line Chart") { modifier, timeSpan, averageItem, state, refresh ->
                AnyChartLineContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.AnyChartColumn.value) {
            BaseStatsDataScreen(navController, title = "Any Android Column Chart") { modifier, timeSpan, averageItem, state, refresh ->
                AnyChartColumnContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.AnyChartBar.value) {
            BaseStatsDataScreen(navController, title = "Any Android Bar Chart") { modifier, timeSpan, averageItem, state, refresh ->
                AnyChartBarContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.HdCharts.value) {
            FlowRowScreen(navController, "HD Charts", HdChartsFunctions.entries)
        }
        composable(route = LineChartRoute.HdChartsLine.value) {
            BaseStatsDataScreen(navController, title = "HD Android Line Chart") { modifier, timeSpan, averageItem, state, refresh ->
                HdChartsLineContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.HdChartsColumn.value) {
            BaseStatsDataScreen(navController, title = "HD Android Column Chart") { modifier, timeSpan, averageItem, state, refresh ->
                HdChartsColumnContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.HdChartsBar.value) {
            BaseStatsDataScreen(navController, title = "HD Android Bar Chart") { modifier, timeSpan, averageItem, state, refresh ->
                HdChartsBarContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.HdChartsRadar.value) {
            BaseStatsDataScreen(navController, title = "HD Android Radar Chart"){modifier, timeSpan, averageItem, state, refresh ->
                HdChartsRadarContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.VicoCharts.value) {
            FlowRowScreen(navController, "Vico Charts", VicoChartsFunctions.entries)
        }
        composable(route = LineChartRoute.VicoChartsLine.value) {
            BaseStatsDataScreen(navController, title = "Vico Android Line Chart") { modifier, timeSpan, averageItem, state, refresh ->
                VicoChartsLineContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.VicoChartsColumn.value) {
            BaseStatsDataScreen(navController, title = "Vico Android Column Chart") { modifier, timeSpan, averageItem, state, refresh ->
                VicoChartsColumnContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.VicoChartsRadar.value) {
            BaseStatsDataScreen(navController, title = "Vico Android Radar Chart") { modifier, timeSpan, averageItem, state, refresh ->
                VicoChartsRadarContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.VicoChartsCombo.value) {
            BaseStatsDataScreen(navController, title = "Vico Android Combo Chart") { modifier, timeSpan, averageItem, state, refresh ->
                VicoChartsComboContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.Charty.value) {
            FlowRowScreen(navController, "Charty Charts", ChartyChartsFunctions.entries)
        }
        composable(route = LineChartRoute.ChartyLine.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Line Chart") { modifier, timeSpan, averageItem, state, refresh ->
                ChartyChartsLineContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.ChartyBar.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Bar Chart") { modifier, timeSpan, averageItem, state, refresh ->
                ChartyChartsBarContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.ChartyColumn.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Column Chart") { modifier, timeSpan, averageItem, state, refresh ->
                ChartyChartsColumnContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.ChartyCombo.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Combo Chart") { modifier, timeSpan, averageItem, state, refresh ->
                ChartyChartsComboContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.ChartyRadar.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Radar Chart") { modifier, timeSpan, averageItem, state, refresh ->
                ChartyChartsRadarContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }

        composable(route = LineChartRoute.YCharts.value) {
            FlowRowScreen(navController, "YCharts", YChartsFunctions.entries)
        }
        composable(route = LineChartRoute.YChartsLine.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Line Chart") { modifier, timeSpan, averageItem, state, refresh ->
                YChartsLineContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.YChartsBar.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Bar Chart") { modifier, timeSpan, averageItem, state, refresh ->
                YChartsBarContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.YChartsColumn.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Column Chart") { modifier, timeSpan, averageItem, state, refresh ->
                YChartsColumnContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.YChartsRadar.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Radar Chart") { modifier, timeSpan, averageItem, state, refresh ->
                YChartsBarContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }
        composable(route = LineChartRoute.YChartsCombo.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Combo Chart") { modifier, timeSpan, averageItem, state, refresh ->
                YChartsComboContent(modifier, timeSpan, averageItem, state, refresh)
            }
        }

    }
}