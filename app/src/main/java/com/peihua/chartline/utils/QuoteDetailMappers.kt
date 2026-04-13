package com.peihua.chartline.utils

import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.peihua.chartline.model.StatsDetail
import com.peihua.chartline.model.StatsItem
import com.peihua.chartline.model.StatsResponse
import com.peihua.chartline.model.ValuesItem

//internal fun convertToQuoteDetail(
//    response: QuotePriceChartResponse,
//    timeSpan: QuoteTimeSpan
//): QuoteDetail {
//    val prices = response.values.map { it.price }
//    val firstPrice = prices.firstOrNull().orZero()
//    val lastPrice = prices.lastOrNull().orZero()
//
//    return QuoteDetail(
//        currentPrice = lastPrice.toCurrency(),
//        openPrice = firstPrice.toCurrency(),
//        closePrice = lastPrice.toCurrency(),
//        highestPrice = prices.maxOrNull().toCurrency(),
//        lowestPrice = prices.minOrNull().toCurrency(),
//        averagePrice = prices.average().toCurrency(),
//        changePrice = (lastPrice - firstPrice).toCurrency(),
//        changeRate = "${firstPrice.changeRateOf(lastPrice)}%",
//        changeStatus = changeStatus(firstPrice, lastPrice),
//        aboutChart = response.description,
//        timeSpan = timeSpan,
//        chartEntries = response.values.map { convertToEntry(it) }
//    )
//}
//
//internal fun convertToEntry(item: QuoteTimePriceItem): Entry =
//    Entry(item.timestamp.toFloat(), item.price.toFloat())
//
//internal fun changeStatus(price: Double, baseline: Double): QuotePriceChangeStatus =
//    if (price >= baseline) QuotePriceChangeStatus.POSITIVE else QuotePriceChangeStatus.NEGATIVE

internal fun convertToEntry(item: StatsItem): Entry =
    Entry(item.timestamp.toFloat(), item.transactionsPerSecond.toFloat())

internal fun <T> convertToStatsDetail(
    response: StatsResponse,
    timeSpan: QuoteTimeSpan,
    rollingAverage: RollingAverage,
    convert: (item: StatsItem) -> T
): StatsDetail<T> {
    val transactions = response.values.map { it.transactionsPerSecond }
    val maxTransaction = transactions.maxOrNull().orZero()

    return StatsDetail(
        title = response.name,
        unit = response.unit,
        period = response.period,
        description = response.description,
        maxTransaction = maxTransaction,
        timeSpan = timeSpan,
        rollingAverage = rollingAverage,
        transactionsEntries = response.values.map { convert(it) }
    )
}

internal fun <T> convertToStatsDetailByLabels(
    response: StatsResponse,
    timeSpan: QuoteTimeSpan,
    rollingAverage: RollingAverage,
    convert: (item: ValuesItem) -> T
): StatsDetail<T> {
    val transactions = response.values.map { it.transactionsPerSecond }
    val maxTransaction = transactions.maxOrNull().orZero()

    return StatsDetail(
        title = response.name,
        unit = response.unit,
        period = response.period,
        description = response.description,
        maxTransaction = maxTransaction,
        timeSpan = timeSpan,
        rollingAverage = rollingAverage,
        transactionsEntries = response.labels.map { convert(it) }
    )
}