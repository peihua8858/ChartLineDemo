package com.peihua.chartline.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.component.Toolbar
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.viewmodel.StatsViewModel
import com.peihua8858.compose.tools.Items
import com.peihua8858.tools.model.ResultData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseStatsDataScreen(
    navController: NavController,
    viewModel: StatsViewModel = hiltViewModel(),
    @StringRes titleRes: Int = 0,
    content: @Composable (
        modifier: Modifier,
        timeSpan: QuoteTimeSpan,
        averageItem: RollingAverage,
        state: ResultData<StatsDetail<Entry>>,
        refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
    ) -> Unit,
) {
    val resource = LocalResources.current
    val title = if (titleRes != 0) resource.getString(titleRes) else ""
    BaseStatsDataScreen(
        navController, viewModel,
        title = title,
        content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseStatsDataScreen(
    navController: NavController,
    viewModel: StatsViewModel = hiltViewModel(),
    title: String = "Base Stats Data Chart",
    content: @Composable (
        modifier: Modifier,
        timeSpan: QuoteTimeSpan,
        averageItem: RollingAverage,
        state: ResultData<StatsDetail<Entry>>,
        refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
    ) -> Unit,
) {
    val refresh = { timeSpan: QuoteTimeSpan, averageItem: RollingAverage ->
        viewModel.fetchStatsDetail(timeSpan, averageItem)
    }
    Toolbar(
        title = title,
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
            content(Modifier, viewModel.timeSpan, viewModel.averageItem, viewModel.state.value, refresh)
        }
    }
}