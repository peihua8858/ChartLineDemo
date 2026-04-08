package com.peihua.chartline.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.model.StatsItem
import com.peihua.chartline.repository.StatsRepository
import com.peihua.chartline.utils.convertToStatsDetail
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.model.request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BarChartStatsViewModel @Inject constructor(val repository: StatsRepository) : ViewModel() {

    private val _barState = mutableStateOf<ResultData<StatsDetail<BarEntry>>>(ResultData.Initialize())
    var timeSpan by mutableStateOf(QuoteTimeSpan.DAY)
    var averageItem by mutableStateOf(RollingAverage.EIGHT_HOURS)
    val barState: State<ResultData<StatsDetail<BarEntry>>> = _barState
    fun fetchStatsDetail(timeSpan: QuoteTimeSpan, rollingAverage: RollingAverage) {
        this.timeSpan = timeSpan
        averageItem = rollingAverage
        request(_barState) {
            convertToStatsDetail(
                repository.transactionsPerSecond(timeSpan.value, rollingAverage.value),
                timeSpan, rollingAverage, ::convertToBarEntry

            )
        }
    }

    internal fun convertToBarEntry(item: StatsItem): BarEntry =
        BarEntry(item.timestamp.toFloat(), item.transactionsPerSecond.toFloat())
}