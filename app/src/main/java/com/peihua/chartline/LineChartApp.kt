package com.peihua.chartline

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peihua.chartline.enums.MainFunctions
import com.peihua.chartline.screen.BaseMarkPriceDataScreen
import com.peihua.chartline.screen.BaseStatsDataScreen
import com.peihua.chartline.screen.FlowRowScreen
import com.peihua.chartline.screen.QuoteScreen
import com.peihua.chartline.screen.SplashScreen
import com.peihua.chartline.screen.aachart.AABarChartScreen
import com.peihua.chartline.screen.aachart.AAChartFunctions
import com.peihua.chartline.screen.aachart.AAColumnChartScreen
import com.peihua.chartline.screen.aachart.AALineChartContent
import com.peihua.chartline.screen.aaychart.AAYChartBarContent
import com.peihua.chartline.screen.aaychart.AAYChartColumnContent
import com.peihua.chartline.screen.aaychart.AAYChartComboContent
import com.peihua.chartline.screen.aaychart.AAYChartFunctions
import com.peihua.chartline.screen.aaychart.AAYChartLineContent
import com.peihua.chartline.screen.aaychart.AAYChartRadarContent
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
import com.peihua.chartline.screen.compose_charts.ComposeChartBarContent
import com.peihua.chartline.screen.compose_charts.ComposeChartColumnContent
import com.peihua.chartline.screen.compose_charts.ComposeChartComboContent
import com.peihua.chartline.screen.compose_charts.ComposeChartLineContent
import com.peihua.chartline.screen.compose_charts.ComposeChartRadarContent
import com.peihua.chartline.screen.compose_charts.ComposeChartsFunctions
import com.peihua.chartline.screen.hdcharts.HdChartsBarContent
import com.peihua.chartline.screen.hdcharts.HdChartsColumnContent
import com.peihua.chartline.screen.hdcharts.HdChartsFunctions
import com.peihua.chartline.screen.hdcharts.HdChartsLineContent
import com.peihua.chartline.screen.hdcharts.HdChartsRadarContent
import com.peihua.chartline.screen.mpChart.BarChartScreen
import com.peihua.chartline.screen.mpChart.LineChartContent
import com.peihua.chartline.screen.mpChart.MPFunctions
import com.peihua.chartline.screen.mpChart.PieChartScreen
import com.peihua.chartline.screen.netguru.NetguruChartBarContent
import com.peihua.chartline.screen.netguru.NetguruChartColumnContent
import com.peihua.chartline.screen.netguru.NetguruChartComboContent
import com.peihua.chartline.screen.netguru.NetguruChartFunctions
import com.peihua.chartline.screen.netguru.NetguruChartLineContent
import com.peihua.chartline.screen.netguru.NetguruChartRadarContent
import com.peihua.chartline.screen.stockchart.StockChartFunctions
import com.peihua.chartline.screen.stockchart.StockChartLineContent
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
            BaseStatsDataScreen(navController, title = "MP Android Line Chart") { modifier, data ->
                LineChartContent(modifier, data)
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
            BaseStatsDataScreen(navController, title = "AA Android Line Chart") { modifier, data ->
                AALineChartContent(modifier, data)
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
            BaseStatsDataScreen(navController, title = "Any Android Line Chart") { modifier, data->
                AnyChartLineContent(modifier, data)
            }
        }
        composable(route = LineChartRoute.AnyChartColumn.value) {
            BaseStatsDataScreen(navController, title = "Any Android Column Chart") { modifier, data ->
                AnyChartColumnContent(modifier, data)
            }
        }
        composable(route = LineChartRoute.AnyChartBar.value) {
            BaseStatsDataScreen(navController, title = "Any Android Bar Chart") { modifier, data->
                AnyChartBarContent(modifier, data)
            }
        }
        composable(route = LineChartRoute.HdCharts.value) {
            FlowRowScreen(navController, "HD Charts", HdChartsFunctions.entries)
        }
        composable(route = LineChartRoute.HdChartsLine.value) {
            BaseStatsDataScreen(navController, title = "HD Android Line Chart") { modifier, data->
                HdChartsLineContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.HdChartsColumn.value) {
            BaseStatsDataScreen(navController, title = "HD Android Column Chart") { modifier,data ->
                HdChartsColumnContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.HdChartsBar.value) {
            BaseStatsDataScreen(navController, title = "HD Android Bar Chart") { modifier,data ->
                HdChartsBarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.HdChartsRadar.value) {
            BaseStatsDataScreen(navController, title = "HD Android Radar Chart") { modifier,data ->
                HdChartsRadarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.VicoCharts.value) {
            FlowRowScreen(navController, "Vico Charts", VicoChartsFunctions.entries)
        }
        composable(route = LineChartRoute.VicoChartsLine.value) {
            BaseStatsDataScreen(navController, title = "Vico Android Line Chart") { modifier,data ->
                VicoChartsLineContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.VicoChartsColumn.value) {
            BaseStatsDataScreen(navController, title = "Vico Android Column Chart") { modifier,data ->
                VicoChartsColumnContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.VicoChartsRadar.value) {
            BaseStatsDataScreen(navController, title = "Vico Android Radar Chart") { modifier,data ->
                VicoChartsRadarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.VicoChartsCombo.value) {
            BaseStatsDataScreen(navController, title = "Vico Android Combo Chart") { modifier,data ->
                VicoChartsComboContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.Charty.value) {
            FlowRowScreen(navController, "Charty Charts", ChartyChartsFunctions.entries)
        }
        composable(route = LineChartRoute.ChartyLine.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Line Chart") { modifier,data ->
                ChartyChartsLineContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ChartyBar.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Bar Chart") { modifier,data ->
                ChartyChartsBarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ChartyColumn.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Column Chart") { modifier,data ->
                ChartyChartsColumnContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ChartyCombo.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Combo Chart") { modifier,data ->
                ChartyChartsComboContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ChartyRadar.value) {
            BaseStatsDataScreen(navController, title = "Charty Android Radar Chart") { modifier,data ->
                ChartyChartsRadarContent(modifier,data)
            }
        }

        composable(route = LineChartRoute.YCharts.value) {
            FlowRowScreen(navController, "YCharts", YChartsFunctions.entries)
        }
        composable(route = LineChartRoute.YChartsLine.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Line Chart") { modifier,data ->
                YChartsLineContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.YChartsBar.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Bar Chart") { modifier,data ->
                YChartsBarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.YChartsColumn.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Column Chart") { modifier,data ->
                YChartsColumnContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.YChartsRadar.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Radar Chart") { modifier,data ->
                YChartsBarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.YChartsCombo.value) {
            BaseStatsDataScreen(navController, title = "YCharts Android Combo Chart") { modifier,data ->
                YChartsComboContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ComposeCharts.value) {
            FlowRowScreen(navController, "Compose Charts", ComposeChartsFunctions.entries)
        }
        composable(route = LineChartRoute.ComposeChartsLine.value) {
            BaseStatsDataScreen(navController, title = "Compose Charts Line Chart") { modifier,data ->
                ComposeChartLineContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ComposeChartsBar.value) {
            BaseStatsDataScreen(navController, title = "Compose Charts Bar Chart") { modifier,data ->
                ComposeChartBarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ComposeChartsColumn.value) {
            BaseStatsDataScreen(navController, title = "Compose Charts Column Chart") { modifier,data ->
                ComposeChartColumnContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ComposeChartsCombo.value) {
            BaseStatsDataScreen(navController, title = "Compose Charts Combo Chart") { modifier,data ->
                ComposeChartComboContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.ComposeChartsRadar.value) {
            BaseStatsDataScreen(navController, title = "Compose Charts Radar Chart") { modifier,data ->
                ComposeChartRadarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.AAYChart.value) {
            FlowRowScreen(navController, "AAy Chart", AAYChartFunctions.entries)
        }
        composable(route = LineChartRoute.AAYChartLine.value) {
            BaseStatsDataScreen(navController, title = "AAY Android Line Chart") { modifier,data ->
                AAYChartLineContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.AAYChartColumn.value) {
            BaseStatsDataScreen(navController, title = "AAY Android Column Chart") { modifier,data ->
                AAYChartColumnContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.AAYChartBar.value) {
            BaseStatsDataScreen(navController, title = "AAY Android Bar Chart") { modifier,data ->
                AAYChartBarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.AAYChartCombo.value) {
            BaseStatsDataScreen(navController, title = "AAY Android Combo Chart") { modifier,data ->
                AAYChartComboContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.AAYChartRadar.value) {
            BaseStatsDataScreen(navController, title = "AAY Android Radar Chart") { modifier,data ->
                AAYChartRadarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.NetguruChart.value) {
            FlowRowScreen(navController, "Netguru Chart", NetguruChartFunctions.entries)
        }
        composable(route = LineChartRoute.NetguruChartLine.value) {
            BaseStatsDataScreen(navController, title = "Netguru Android Line Chart") { modifier,data ->
                NetguruChartLineContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.NetguruChartColumn.value) {
            BaseStatsDataScreen(navController, title = "Netguru Android Column Chart") { modifier,data ->
                NetguruChartColumnContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.NetguruChartBar.value) {
            BaseStatsDataScreen(navController, title = "Netguru Android Bar Chart") { modifier,data ->
                NetguruChartBarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.NetguruChartCombo.value) {
            BaseStatsDataScreen(navController, title = "Netguru Android Combo Chart") { modifier,data ->
                NetguruChartComboContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.NetguruChartRadar.value) {
            BaseStatsDataScreen(navController, title = "Netguru Android Radar Chart") { modifier,data ->
                NetguruChartRadarContent(modifier,data)
            }
        }
        composable(route = LineChartRoute.StockChart.value) {
            FlowRowScreen(navController, "Stock Chart", StockChartFunctions.entries)
        }
        composable(route = LineChartRoute.StockChartLine.value) {
            BaseMarkPriceDataScreen(navController, title = "Stock Chart Line") { modifier,data ->
                StockChartLineContent(modifier,data)
            }
        }

    }
}