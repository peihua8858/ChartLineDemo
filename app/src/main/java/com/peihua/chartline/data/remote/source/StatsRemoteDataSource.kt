package com.peihua.chartline.data.remote.source

import com.peihua.chartline.model.StatsResponse
import com.peihua.chartline.data.remote.serveice.StatsService
import javax.inject.Inject

class StatsRemoteDataSource @Inject constructor(private val statsService: StatsService) {
    suspend fun transactionsPerSecond(
        timeSpan: String,
        rollingAverage: String
    ): StatsResponse = statsService.transactionsPerSecond(timeSpan, rollingAverage)
}