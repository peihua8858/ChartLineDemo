package com.peihua.chartline.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieEntry
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.model.StatsItem
import com.peihua.chartline.model.ValuesItem
import com.peihua.chartline.repository.MpBarChartRepository
import com.peihua.chartline.repository.MpPieChartRepository
import com.peihua.chartline.repository.StatsRepository
import com.peihua.chartline.utils.convertToStatsDetail
import com.peihua.chartline.utils.convertToStatsDetailByLabels
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.model.request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PieChartViewModel @Inject constructor(val repository: MpPieChartRepository) : ViewModel() {

    private val _barState = mutableStateOf<ResultData<StatsDetail<PieEntry>>>(ResultData.Initialize())
    var timeSpan by mutableIntStateOf(12)
    var averageItem by mutableIntStateOf(50)
    val barState: State<ResultData<StatsDetail<PieEntry>>> = _barState
    fun fetchStatsDetail(count: Int, range: Int) {
        this.timeSpan = count
        averageItem = range
        request(_barState) {
            convertToStatsDetailByLabels(
                repository.transactions(count, range),
                QuoteTimeSpan.DAY, RollingAverage.ONE_DAY , ::convertToBarEntry

            )
        }
    }

    internal fun convertToBarEntry(item: ValuesItem): PieEntry =
        PieEntry(item.value.toFloat(),item.label)
}