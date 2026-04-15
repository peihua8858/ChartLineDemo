package com.peihua.chartline.data.remote.serveice

import com.peihua.chartline.model.StatsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StatsService {
    @GET("charts/transactions-per-second")
    suspend fun transactionsPerSecond(
        @Query("timespan")
        timeSpan: String,
        @Query("rollingAverage")
        rollingAverage: String,
    ): StatsResponse
}