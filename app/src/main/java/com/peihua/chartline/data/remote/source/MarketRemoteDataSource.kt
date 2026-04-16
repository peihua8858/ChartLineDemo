package com.peihua.chartline.data.remote.source

import com.peihua.chartline.model.MarketPriceChartResponse
import com.peihua.chartline.data.remote.serveice.MarketService
import retrofit2.http.Query
import javax.inject.Inject

class MarketRemoteDataSource @Inject constructor(
    private val marketService: MarketService,
) {

    suspend fun fetchMarketPriceChart(timespan: String, rollingAverage: String): MarketPriceChartResponse =
        marketService.fetchMarketPriceChart(timespan,rollingAverage)
}