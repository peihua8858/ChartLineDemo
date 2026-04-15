package com.peihua.chartline.model

import com.github.mikephil.charting.data.Entry
import com.google.gson.annotations.SerializedName
import com.peihua.chartline.enums.MarketInformationChangeStatus
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.utils.changeRateOf
import com.peihua.chartline.utils.orZero
import com.peihua.chartline.utils.toCurrency

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
    val currentPrice: String,
    val openPrice: String,
    val closePrice: String,
    val highPrice: String,
    val lowPrice: String,
    val averagePrice: String,
    val changePrice: String,
    val changeRate: String,
    val changeStatus: MarketInformationChangeStatus,
    val aboutChart: String,
    val timespan: QuoteTimeSpan,
    val chartEntries: List<Entry>,
)


fun MarketPriceChartResponse.toMarketInformation(timespan: QuoteTimeSpan): MarketInformation {
    val prices = values.map { it.price }
    val firstPrice = prices.firstOrNull().orZero()
    val lastPrice = prices.lastOrNull().orZero()

    return MarketInformation(
        currentPrice = lastPrice.toCurrency(),
        openPrice = firstPrice.toCurrency(),
        closePrice = lastPrice.toCurrency(),
        highPrice = prices.maxOrNull().toCurrency(),
        lowPrice = prices.minOrNull().toCurrency(),
        averagePrice = prices.average().toCurrency(),
        changePrice = (lastPrice.minus(firstPrice)).toCurrency(),
        changeRate = "${firstPrice.changeRateOf(lastPrice)}%",
        changeStatus = if (lastPrice >= firstPrice)
            MarketInformationChangeStatus.POSITIVE
        else
            MarketInformationChangeStatus.NEGATIVE,
        aboutChart = description,
        timespan = timespan,
        chartEntries = values.map { mapOnMarketPriceValueResponse(it) })
}

fun mapOnMarketPriceValueResponse(marketPriceValueResponse: MarketPriceValueResponse): Entry {
    return Entry(marketPriceValueResponse.timestamp, marketPriceValueResponse.price.toFloat())
}