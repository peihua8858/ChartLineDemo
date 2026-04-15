package com.peihua.chartline.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.model.MarketInformation
import com.peihua.chartline.model.toMarketInformation
import com.peihua.chartline.data.respository.MarketRepository
import com.peihua8858.tools.model.ResultData
import com.peihua8858.tools.model.request
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val marketRepository: MarketRepository,
) : ViewModel() {

    private val _uiState = mutableStateOf<ResultData<MarketInformation>>(ResultData.Initialize())

    val uiState: State<ResultData<MarketInformation>> = _uiState

    fun getMarketInformation(timeRange: QuoteTimeSpan) {
        request(_uiState) {
            marketRepository.fetchMarketPriceChart(timeRange.value).toMarketInformation(timeRange)
        }
    }
}