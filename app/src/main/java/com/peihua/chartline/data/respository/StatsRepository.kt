package com.peihua.chartline.data.respository

import com.peihua.chartline.data.remote.source.StatsRemoteDataSource
import com.peihua.chartline.model.StatsResponse
import javax.inject.Inject

interface StatsRepository {
    suspend fun transactionsPerSecond(timeSpan: String, rollingAverage: String): StatsResponse
}

class StatsRepositoryImpl @Inject constructor(
    private val statsRemoteDataSource: StatsRemoteDataSource,
) : StatsRepository {
    override suspend fun transactionsPerSecond(
        timeSpan: String,
        rollingAverage: String,
    ): StatsResponse {
        return statsRemoteDataSource.transactionsPerSecond(timeSpan, rollingAverage)
    }
}