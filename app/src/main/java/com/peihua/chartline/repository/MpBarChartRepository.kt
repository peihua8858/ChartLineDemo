package com.peihua.chartline.repository

import com.peihua.chartline.model.StatsResponse
import com.peihua.chartline.qualifier.IoDispatcher
import com.peihua.chartline.source.MpBarChartRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MpBarChartRepository {
    suspend fun transactions(count: Int, range: Int): StatsResponse
}

class MpBarChartRepositoryImpl @Inject constructor(
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: MpBarChartRemoteDataSource,
) : MpBarChartRepository {
    override suspend fun transactions(
        count: Int, range: Int,
    ): StatsResponse = withContext(ioDispatcher) {
        remoteDataSource.transactions(count, range)
    }
}