package com.peihua.chartline.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarEntry
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.model.StatsItem
import com.peihua.chartline.data.respository.MpBarChartRepository
import com.peihua.chartline.utils.convertToStatsDetail
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.model.request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BarChartStatsViewModel @Inject constructor(val repository: MpBarChartRepository) : ViewModel() {

    private val _barState = mutableStateOf<ResultData<StatsDetail<BarEntry>>>(ResultData.Initialize())
    var timeSpan by mutableIntStateOf(12)
    var averageItem by mutableIntStateOf(50)
    val barState: State<ResultData<StatsDetail<BarEntry>>> = _barState
    fun fetchStatsDetail(count: Int, range: Int) {
        this.timeSpan = count
        averageItem = range
        request(_barState) {
            convertToStatsDetail(
                repository.transactions(count, range),
                QuoteTimeSpan.DAY, RollingAverage.ONE_DAY , ::convertToBarEntry

            )
        }
    }

    internal fun convertToBarEntry(item: StatsItem): BarEntry =
        BarEntry(item.timestamp.toFloat(),item.transactionsPerSecond.toFloat())
}