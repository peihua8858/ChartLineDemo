package com.peihua.chartline

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
import com.peihua.chartline.screen.aachart.AALineChartScreen
import com.peihua.chartline.screen.mpChart.BarChartScreen
import com.peihua.chartline.screen.mpChart.MPFunctions

@Composable
fun LineChartApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LineChartRoute.Splash.value) {
        composable(route = LineChartRoute.Splash.value) {
            SplashScreen(navController = navController)
        }
        composable(route = LineChartRoute.Main.value) {
            FlowRowScreen(navController, MainFunctions.entries)
        }
        composable(route = LineChartRoute.MpChart.value) {
            FlowRowScreen(navController, MPFunctions.entries)
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
        composable(route = LineChartRoute.AAChartCore.value) {
            FlowRowScreen(navController, AAChartFunctions.entries)
        }
        composable(route = LineChartRoute.AALineChart.value) {
            AALineChartScreen(navController)
        }
        composable(route = LineChartRoute.AABarChart.value) {
            AABarChartScreen(navController)
        }
    }
}