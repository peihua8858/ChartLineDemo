package com.peihua.chartline.di

import com.peihua.chartline.data.remote.serveice.MarketService
import com.peihua.chartline.data.remote.serveice.StatsService
import com.peihua.chartline.data.respository.MarketRepository
import com.peihua.chartline.data.respository.MarketRepositoryImpl
import com.peihua.chartline.data.respository.MpBarChartRepository
import com.peihua.chartline.data.respository.MpBarChartRepositoryImpl
import com.peihua.chartline.data.respository.MpPieChartRepository
import com.peihua.chartline.data.respository.MpPieChartRepositoryImpl
import com.peihua.chartline.data.respository.StatsRepository
import com.peihua.chartline.data.respository.StatsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class ChartModule {
    @Binds
    abstract fun bindStatsRepository(impl: StatsRepositoryImpl): StatsRepository

    @Binds
    abstract fun bindMarketRepository(impl: MarketRepositoryImpl): MarketRepository

    @Binds
    abstract fun bindMpBarChartRepository(impl: MpBarChartRepositoryImpl): MpBarChartRepository

    @Binds
    abstract fun bindMpPieChartRepository(impl: MpPieChartRepositoryImpl): MpPieChartRepository

    companion object {
        @Provides
        fun provideStatsService(retrofit: Retrofit): StatsService =
            retrofit.create(StatsService::class.java)

        @Provides
        fun provideMarketService(retrofit: Retrofit): MarketService =
            retrofit.create(MarketService::class.java)
    }
}
