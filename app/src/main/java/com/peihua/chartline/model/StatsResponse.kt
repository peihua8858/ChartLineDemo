package com.peihua.chartline.model

import com.google.gson.annotations.SerializedName
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage

data class StatsResponse(
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
    val values: List<StatsItem> = mutableListOf(),
    @SerializedName(value = "labels")
    val labels: List<ValuesItem> = mutableListOf(),
)

data class StatsItem(
    @SerializedName(value = "x") val timestamp: Long,
    @SerializedName(value = "y") val transactionsPerSecond: Double,

    )

data class ValuesItem(
    @SerializedName(value = "value") val value: Double,
    @SerializedName(value = "label") val label: String,

    )

data class StatsDetail<T>(
    val title: String,
    val unit: String,
    val period: String,
    val description: String,
    val maxTransaction: Double,
    val timeSpan: QuoteTimeSpan,
    val rollingAverage: RollingAverage,
    val transactionsEntries: List<T>,
) {
    val stats: Stats get() = Stats(title, unit, period, description)

    val detail: Detail<T>
        get() = Detail<T>(
            maxTransaction,
            timeSpan,
            rollingAverage,
            transactionsEntries
        )

    data class Stats(
        val title: String,
        val unit: String,
        val period: String,
        val description: String,
    ) {
        fun string(): String = "$title: $unit in $period"
    }

    data class Detail<T>(
        val maxTransaction: Double,
        val timeSpan: QuoteTimeSpan,
        val rollingAverage: RollingAverage,
        val transactionsEntries: List<T>,
    )
}