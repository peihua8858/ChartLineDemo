package com.peihua.chartline.data.respository

import com.peihua.chartline.model.StatsResponse
import com.peihua.chartline.qualifier.IoDispatcher
import com.peihua.chartline.source.MpPieChartRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MpPieChartRepository {
    suspend fun transactions(count: Int, range: Int): StatsResponse
}

class MpPieChartRepositoryImpl @Inject constructor(
    private val remoteDataSource: MpPieChartRemoteDataSource,
) : MpPieChartRepository {
    override suspend fun transactions(
        count: Int, range: Int,
    ): StatsResponse {
        return remoteDataSource.transactions(count, range)
    }
}