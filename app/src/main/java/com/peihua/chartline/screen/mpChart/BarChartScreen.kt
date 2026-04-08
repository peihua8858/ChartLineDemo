package com.peihua.chartline.screen.mpChart

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineData
import com.peihua.chartline.component.BarChart
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LineChart
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.component.Toolbar
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.utils.barDataSet
import com.peihua.chartline.utils.lineDataSet
import com.peihua.chartline.viewmodel.BarChartStatsViewModel
import com.peihua.chartline.viewmodel.StatsViewModel
import com.peihua8858.compose.tools.Items
import com.peihua8858.tools.model.ResultData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarChartScreen(navController: NavController, viewModel: BarChartStatsViewModel = hiltViewModel()) {
    val refresh = { timeSpan: QuoteTimeSpan, averageItem: RollingAverage ->
        viewModel.fetchStatsDetail(timeSpan, averageItem)
    }
    Toolbar(
        title = "ChartLine",
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray),
        navigateUp = { navController.popBackStack() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(modifier = Modifier.padding(start = 32.dp, top = 16.dp, end = 32.dp)) {
                Text("Time span:")
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
                ) {
                    Items(QuoteTimeSpan.entries) { item ->
                        val isSelected = viewModel.timeSpan == item
                        Text(
                            color = if (isSelected) Color.Black else Color.Gray,
                            text = item.valueName, modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, if (isSelected) Color.Black else Color.Gray, shape = RoundedCornerShape(8.dp))
                                .clickable {
                                    refresh(item, viewModel.averageItem)
                                }
                                .padding(start = 16.dp, end = 16.dp))
                    }
                }
            }
            Row(modifier = Modifier.padding(start = 32.dp, top = 16.dp, end = 32.dp)) {
                Text("Rolling avg:")
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
                ) {
                    Items(RollingAverage.entries) { item ->
                        val isSelected = viewModel.averageItem == item
                        Text(
                            color = if (isSelected) Color.Black else Color.Gray,
                            text = item.valueName, modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, if (isSelected) Color.Black else Color.Gray, shape = RoundedCornerShape(8.dp))
                                .clickable {
                                    refresh(viewModel.timeSpan, item)
                                }
                                .padding(start = 16.dp, end = 16.dp))
                    }
                }
            }
            LineChartContent(
                modifier = Modifier,
                timeSpan = viewModel.timeSpan,
                averageItem = viewModel.averageItem,
                state = viewModel.barState.value,
                refresh = refresh
            )
        }

    }
}

@Composable
private fun LineChartContent(
    modifier: Modifier = Modifier,
    timeSpan: QuoteTimeSpan,
    averageItem: RollingAverage,
    state: ResultData<StatsDetail<BarEntry>>,
    refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is ResultData.Success -> {
                BarChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    data = BarData(state.data.barDataSet(context))
                )
            }

            is ResultData.Failure -> {
                ErrorView(
                    retry = {
                        refresh(timeSpan, averageItem)
                    })
            }

            is ResultData.Initialize -> {
                refresh(timeSpan, averageItem)
            }

            is ResultData.Starting -> {
                LoadingView(modifier = Modifier)
            }
        }
    }

}