package com.peihua.chartline.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.model.MarketInformation
import com.peihua.chartline.model.toMarketInformation
import com.peihua.chartline.data.respository.MarketRepository
import com.peihua.chartline.enums.RollingAverage
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.model.request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val marketRepository: MarketRepository,
) : ViewModel() {

    private val _uiState = mutableStateOf<ResultData<MarketInformation>>(ResultData.Initialize())

    val state: State<ResultData<MarketInformation>> = _uiState
    var timeSpan by mutableStateOf(QuoteTimeSpan.ONE_YEAR)
    var averageItem by mutableStateOf(RollingAverage.EIGHT_HOURS)
    fun fetchMarketInformation(timeSpan: QuoteTimeSpan, rollingAverage: RollingAverage) {
        this.timeSpan = timeSpan
        averageItem = rollingAverage
        request(_uiState) {
            marketRepository.fetchMarketPriceChart(timeSpan.value, rollingAverage.value).toMarketInformation(timeSpan)
        }
    }
}