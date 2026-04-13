package com.peihua.chartline.screen.mpChart

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.Fill
import com.peihua.chartline.component.BarChart
import com.peihua.chartline.component.CustomSlider
import com.peihua.chartline.component.ErrorView
import com.peihua.chartline.component.LoadingView
import com.peihua.chartline.component.PieChart
import com.peihua.chartline.component.Toolbar
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.ui.theme.Purple200
import com.peihua.chartline.ui.theme.Purple500
import com.peihua.chartline.ui.theme.Purple700
import com.peihua.chartline.utils.barDataSet
import com.peihua.chartline.viewmodel.BarChartStatsViewModel
import com.peihua.chartline.viewmodel.PieChartViewModel
import com.peihua8858.compose.tools.isSystemDarkMode
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.model.isStarting

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PieChartScreen(navController: NavController, viewModel: PieChartViewModel = hiltViewModel()) {
    val state = viewModel.barState.value
    val refresh = { count: Int, range: Int ->
        if (!state.isStarting()) {
            viewModel.fetchStatsDetail(count, range)
        }
    }
    Toolbar(
        title = "MP Android Pie Chart",
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray),
        navigateUp = { navController.popBackStack() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.padding(start = 32.dp, top = 16.dp, end = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Time span:")
                CustomSlider(
                    modifier = Modifier
                        .padding(8.dp),
                    value = viewModel.timeSpan.toFloat(),
                    thumbText = { it.toInt().toString() },
                    valueRange = 0f..100f
                ) {
                    refresh(it.toInt(), viewModel.averageItem)
                }
            }
            Row(
                modifier = Modifier.padding(start = 32.dp, top = 16.dp, end = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Rolling avg:")
                CustomSlider(
                    modifier = Modifier
                        .padding(8.dp),
                    value = viewModel.averageItem.toFloat(),
                    thumbText = { it.toInt().toString() },
                    valueRange = 0f..100f
                ) {
                    refresh(viewModel.timeSpan, it.toInt())
                }
            }
            BarChartContent(
                modifier = Modifier,
                count = viewModel.timeSpan,
                range = viewModel.averageItem,
                state = state,
                refresh = refresh
            )
        }

    }
}

@Composable
private fun BarChartContent(
    modifier: Modifier = Modifier,
    count: Int,
    range: Int,
    state: ResultData<StatsDetail<PieEntry>>,
    refresh: (count: Int, range: Int) -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is ResultData.Success -> {
                PieChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    centerText = "Pie Chart ",
                    data = PieData(state.data.pieDataSet(context)).apply {
                        setValueFormatter(PercentFormatter());
                        setValueTextSize(11f);
                        setValueTextColor(android.graphics.Color.WHITE);
//                        setValueTypeface(tfLight);
                    }
                )
            }

            is ResultData.Failure -> {
                ErrorView(
                    retry = {
                        refresh(count, range)
                    })
            }

            is ResultData.Initialize -> {
                refresh(count, range)
            }

            is ResultData.Starting -> {
                LoadingView(modifier = Modifier)
            }
        }
    }

}


fun StatsDetail<PieEntry>.pieDataSet(context: Context): PieDataSet {
    return PieDataSet(transactionsEntries, "Election Results").apply {
        Log.d("BarChart", "barDataSet: ${this.values.size}")
        Log.d("BarChart", "barDataSet: ${this.values.joinToString(",")}")
        setDrawValues(false)
        setValueTextSize(10f);
        setDrawIcons(true);
        setSliceSpace(3f);
        setSelectionShift(5f);
        //
        this.setColors(*ColorTemplate.MATERIAL_COLORS)
//        color = (if (context.isSystemDarkMode) Purple500 else Purple200).toArgb()
    }
}