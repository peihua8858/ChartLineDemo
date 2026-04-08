package com.peihua.chartline.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.repository.StatsRepository
import com.peihua.chartline.utils.convertToStatsDetail
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.model.request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(val repository: StatsRepository) : ViewModel() {
    private val _state = mutableStateOf<ResultData<StatsDetail>>(ResultData.Initialize())
    var timeSpan by mutableStateOf(QuoteTimeSpan.DAY)
    var averageItem by mutableStateOf(RollingAverage.EIGHT_HOURS)
    val state: State<ResultData<StatsDetail>> = _state
    fun fetchStatsDetail(timeSpan: QuoteTimeSpan, rollingAverage: RollingAverage) {
        this.timeSpan = timeSpan
        averageItem = rollingAverage
        request(_state) {
            convertToStatsDetail(
                repository.transactionsPerSecond(timeSpan.value, rollingAverage.value),
                timeSpan, rollingAverage
            )
        }
    }
}