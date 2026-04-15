package com.peihua.chartline.data.remote.source

import com.peihua.chartline.model.MarketPriceChartResponse
import com.peihua.chartline.data.remote.serveice.MarketService
import javax.inject.Inject

class MarketRemoteDataSource @Inject constructor(
    private val marketService: MarketService,
) {

    suspend fun fetchMarketPriceChart(timespan: String): MarketPriceChartResponse =
        marketService.fetchMarketPriceChart(timespan)
}