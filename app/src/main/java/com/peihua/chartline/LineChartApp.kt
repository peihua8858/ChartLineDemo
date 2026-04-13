package com.peihua.chartline

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.peihua.chartline.enums.MainFunctions
import com.peihua.chartline.screen.FlowRowScreen
import com.peihua.chartline.screen.mpChart.LineChartScreen
import com.peihua.chartline.screen.QuoteScreen
import com.peihua.chartline.screen.SplashScreen
import com.peihua.chartline.screen.aachart.AABarChartScreen
import com.peihua.chartline.screen.aachart.AAChartFunctions
import com.peihua.chartline.screen.aachart.AAColumnChartScreen
import com.peihua.chartline.screen.aachart.AALineChartScreen
import com.peihua.chartline.screen.anychart.AnyChartBarScreen
import com.peihua.chartline.screen.anychart.AnyChartColumnScreen
import com.peihua.chartline.screen.anychart.AnyChartFunctions
import com.peihua.chartline.screen.anychart.AnyChartLineScreen
import com.peihua.chartline.screen.mpChart.BarChartScreen
import com.peihua.chartline.screen.mpChart.MPFunctions
import com.peihua.chartline.screen.mpChart.PieChartScreen
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
            FlowRowScreen(navController,"Charts", MainFunctions.entries){
                val popped =  navController.popBackStack()
                if (!popped) {
                    context.finish()
                }
            }
        }
        composable(route = LineChartRoute.MpChart.value) {
            FlowRowScreen(navController,"MP Android Chart", MPFunctions.entries)
        }
        composable(route = LineChartRoute.Quote.value) {
            QuoteScreen(navController = navController)
        }
        composable(route = LineChartRoute.LineChart.value) {
            LineChartScreen(navController = navController)
        }
        composable(route = LineChartRoute.BarChart.value) {
            BarChartScreen(navController = navController)
        }
        composable(route = LineChartRoute.PieChart.value) {
            PieChartScreen(navController)
        }
        composable(route = LineChartRoute.AAChartCore.value) {
            FlowRowScreen(navController,"AA Chart Core", AAChartFunctions.entries)
        }
        composable(route = LineChartRoute.AALineChart.value) {
            AALineChartScreen(navController)
        }
        composable(route = LineChartRoute.AAColumnChart.value) {
            AAColumnChartScreen(navController)
        }
        composable(route = LineChartRoute.AABarChart.value) {
            AABarChartScreen(navController)
        }
        composable(route = LineChartRoute.AnyChart.value) {
            FlowRowScreen(navController,"Any Chart", AnyChartFunctions.entries)
        }
        composable(route = LineChartRoute.AnyChartLine.value) {
            AnyChartLineScreen(navController)
        }
        composable(route = LineChartRoute.AnyChartColumn.value) {
            AnyChartColumnScreen(navController)
        }
        composable(route = LineChartRoute.AnyChartBar.value) {
            AnyChartBarScreen(navController)
        }
    }
}