package com.peihua.chartline.screen.anychart

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
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.core.Chart
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.component.AnyChartView
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.component.Toolbar
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.viewmodel.StatsViewModel
import com.peihua8858.compose.tools.Items
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.utils.dLog
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnyChartColumnScreen(navController: NavController, viewModel: StatsViewModel = hiltViewModel()) {
    val refresh = { timeSpan: QuoteTimeSpan, averageItem: RollingAverage ->
        viewModel.fetchStatsDetail(timeSpan, averageItem)
    }
    Toolbar(
        title = "AA Android Line Chart",
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
            AAColumnChartContent(
                modifier = Modifier,
                timeSpan = viewModel.timeSpan,
                averageItem = viewModel.averageItem,
                state = viewModel.state.value,
                refresh = refresh
            )
        }

    }
}

@Composable
private fun AAColumnChartContent(
    modifier: Modifier = Modifier,
    timeSpan: QuoteTimeSpan,
    averageItem: RollingAverage,
    state: ResultData<StatsDetail<Entry>>,
    refresh: (timeSpan: QuoteTimeSpan, averageItem: RollingAverage) -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is ResultData.Success -> {
                AnyChartView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                ){
                    state.data.anyCharModelColumnData(context)
                }
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
private fun StatsDetail<Entry>.anyCharModelColumnData(context: android.content.Context): Chart {
    val transactionsEntries = this.transactionsEntries
    val values = mutableListOf<DataEntry>()
    val format = DateTimeFormatter.ofPattern("hh:mm:ss")
    transactionsEntries.forEach {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = (it.x * 1000L).toLong()
        val time = ZonedDateTime.of(
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH],
            calendar[Calendar.HOUR],
            calendar[Calendar.MINUTE],
            calendar[Calendar.SECOND],
            calendar[Calendar.MILLISECOND], ZoneId.systemDefault()
        ).format(format)
        values.add(ValueDataEntry(time, it.y))
    }
    dLog { "anyCharModelLineData>>>values: ${values.joinToString { it.generateJs()  }}" }
    val cartesian = AnyChart.column()
    val column = cartesian.column(values)
    column.tooltip()
        .titleFormat("{%X}")
        .position(Position.CENTER_BOTTOM)
        .anchor(Anchor.CENTER_BOTTOM)
        .offsetX(0f)
        .offsetY(5f)
        .format("\${%Value}{groupsSeparator: }")
    cartesian.animation(true)
    cartesian.title("Top 10 Cosmetic Products by Revenue")

    cartesian.yScale().minimum(0.0)

    cartesian.yAxis(0).labels().format("\${%Value}{groupsSeparator: }")

    cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
    cartesian.interactivity().hoverMode(HoverMode.BY_X)

    cartesian.xAxis(0).title("Product")
    cartesian.yAxis(0).title("Revenue")
    return cartesian.apply {
        legend().enabled(true)

    }
}