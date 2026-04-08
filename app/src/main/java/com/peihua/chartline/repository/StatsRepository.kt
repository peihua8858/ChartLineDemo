package com.peihua.chartline.repository

import com.peihua.chartline.model.StatsResponse
import com.peihua.chartline.qualifier.IoDispatcher
import com.peihua.chartline.source.StatsRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface StatsRepository {
    suspend fun transactionsPerSecond(timeSpan: String, rollingAverage: String): StatsResponse
}

class StatsRepositoryImpl @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val statsRemoteDataSource: StatsRemoteDataSource
) : StatsRepository {
    override suspend fun transactionsPerSecond(
        timeSpan: String,
        rollingAverage: String
    ): StatsResponse = withContext(ioDispatcher) {
        statsRemoteDataSource.transactionsPerSecond(timeSpan, rollingAverage)
    }
}