package com.peihua.chartline.model

import com.github.mikephil.charting.data.Entry
import com.google.gson.annotations.SerializedName
import com.peihua.chartline.enums.MarketInformationChangeStatus
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.utils.changeRateOf
import com.peihua.chartline.utils.orZero
import com.peihua.chartline.utils.toCurrency
import com.peihua8858.tools.utils.dLog

data class MarketPriceChartResponse(
    @SerializedName(value = "description")
    val description: String,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "period")
    val period: String,
    @SerializedName(value = "status")
    val status: String,
    @SerializedName(value = "unit")
    val unit: String,
    @SerializedName(value = "values")
    val values: List<MarketPriceValueResponse>,
)


data class MarketPriceValueResponse(
    @SerializedName(value = "x")
    val timestamp: Float,
    @SerializedName(value = "y")
    val price: Double,
)

data class MarketInformation(
    val currentPrice: Double,
    val openPrice: Double,
    val closePrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val averagePrice: Double,
    val changePrice: Double,
    val changeRate: Double,
    val changeStatus: MarketInformationChangeStatus,
    val aboutChart: String,
    val timespan: QuoteTimeSpan,
    val chartEntries: List<Entry>,
){
    val currentPriceFormat: String
        get() = currentPrice.toCurrency()
    val openPriceFormat: String
        get() = openPrice.toCurrency()
    val closePriceFormat: String
        get() = closePrice.toCurrency()
    val highPriceFormat: String
        get() = highPrice.toCurrency()
    val lowPriceFormat: String
        get() = lowPrice.toCurrency()
    val averagePriceFormat: String
        get() = averagePrice.toCurrency()
    val changePriceFormat: String
        get() = changePrice.toCurrency()
    val changeRateFormat: String
        get() = "${openPrice.changeRateOf(closePrice)}%"
}


fun MarketPriceChartResponse.toMarketInformation(timespan: QuoteTimeSpan): MarketInformation {
    val prices = values.map { it.price }
    val firstPrice = prices.firstOrNull().orZero()
    val lastPrice = prices.lastOrNull().orZero()
    dLog { "modelToLineData>>>values.size:${values.size}" }
    return MarketInformation(
        currentPrice = lastPrice,
        openPrice = firstPrice,
        closePrice = lastPrice,
        highPrice = prices.maxOrNull()?:0.0,
        lowPrice = prices.minOrNull()?:0.0,
        averagePrice = prices.average(),
        changePrice = (lastPrice.minus(firstPrice)),
        changeRate = firstPrice.changeRateOf(lastPrice),
        changeStatus = if (lastPrice >= firstPrice)
            MarketInformationChangeStatus.POSITIVE
        else
            MarketInformationChangeStatus.NEGATIVE,
        aboutChart = description,
        timespan = timespan,
        chartEntries = values.map { mapOnMarketPriceValueResponse(it) }).apply {
        dLog { "modelToLineData>>>1chartEntries.size:${chartEntries.size}" }
    }
}

fun mapOnMarketPriceValueResponse(marketPriceValueResponse: MarketPriceValueResponse): Entry {
    return Entry(marketPriceValueResponse.timestamp, marketPriceValueResponse.price.toFloat())
}