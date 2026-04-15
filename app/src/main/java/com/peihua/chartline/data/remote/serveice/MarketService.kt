package com.peihua.chartline.data.remote.serveice

import com.peihua.chartline.model.MarketPriceChartResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketService {

    @GET("charts/market-price")
    suspend fun fetchMarketPriceChart(@Query("timespan") timespan: String): MarketPriceChartResponse
}