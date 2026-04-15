package com.peihua.chartline.data.respository

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
    private val remoteDataSource: MpBarChartRemoteDataSource,
) : MpBarChartRepository {
    override suspend fun transactions(
        count: Int, range: Int,
    ): StatsResponse {
        return remoteDataSource.transactions(count, range)
    }
}