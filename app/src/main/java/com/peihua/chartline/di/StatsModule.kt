package com.peihua.chartline.di

import com.peihua.chartline.data.respository.MpBarChartRepository
import com.peihua.chartline.data.respository.MpBarChartRepositoryImpl
import com.peihua.chartline.data.respository.MpPieChartRepository
import com.peihua.chartline.data.respository.MpPieChartRepositoryImpl
import com.peihua.chartline.data.respository.StatsRepository
import com.peihua.chartline.data.respository.StatsRepositoryImpl
import com.peihua.chartline.data.remote.serveice.StatsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
//
//@Module
//@InstallIn(ViewModelComponent::class)
//abstract class StatsModule {
//
//    @Binds
//    abstract fun bindStatsRepository(impl: StatsRepositoryImpl): StatsRepository
//    @Binds
//    abstract fun bindMpBarChartRepository(impl: MpBarChartRepositoryImpl): MpBarChartRepository
//    @Binds
//    abstract fun bindMpPieChartRepository(impl: MpPieChartRepositoryImpl): MpPieChartRepository
//    companion object {
//        @Provides
//        fun provideStatsService(retrofit: Retrofit): StatsService =
//            retrofit.create(StatsService::class.java)
//    }
//}