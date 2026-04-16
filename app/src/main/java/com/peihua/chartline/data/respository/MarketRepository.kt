package com.peihua.chartline.data.respository

import androidx.annotation.VisibleForTesting
import com.peihua.chartline.data.remote.source.MarketRemoteDataSource
import com.peihua.chartline.model.MarketPriceChartResponse
import javax.inject.Inject

interface MarketRepository {
    suspend fun fetchMarketPriceChart(timespan: String,rollingAverage: String): MarketPriceChartResponse
}

class MarketRepositoryImpl @Inject constructor(
    private val marketRemoteDataSource: MarketRemoteDataSource,
) : MarketRepository {

    override suspend fun fetchMarketPriceChart(timespan: String,rollingAverage: String): MarketPriceChartResponse {
        return fetchMarketDataFromRemote(timespan,rollingAverage)
    }

    @VisibleForTesting
    suspend fun fetchMarketDataFromRemote(timeSpan: String,rollingAverage: String) =
        marketRemoteDataSource.fetchMarketPriceChart(timeSpan,rollingAverage)
            .also { marketRemoteData ->
                insertMarketResponse(
                    timeSpan = timeSpan,
                    remoteData = marketRemoteData
                )
            }

    @VisibleForTesting
    suspend fun insertMarketResponse(timeSpan: String, remoteData: MarketPriceChartResponse) {
    }

    companion object {
        private const val EXPIRED_TIME = 1000L * 60
    }
}