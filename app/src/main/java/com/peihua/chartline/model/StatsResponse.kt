package com.peihua.chartline.model
import com.github.mikephil.charting.data.Entry
import com.peihua.chartline.enums.QuoteTimeSpan
import com.peihua.chartline.enums.RollingAverage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class StatsResponse(
    @Json(name = "description")
    val description: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "period")
    val period: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "unit")
    val unit: String,
    @Json(name = "values")
    val values: List<StatsItem>
)

@JsonClass(generateAdapter = true)
data class StatsItem(
    @Json(name = "x") val timestamp: Long,
    @Json(name = "y") val transactionsPerSecond: Double
)

data class StatsDetail(
    val title: String,
    val unit: String,
    val period: String,
    val description: String,
    val maxTransaction: Double,
    val timeSpan: QuoteTimeSpan,
    val rollingAverage: RollingAverage,
    val transactionsEntries: List<Entry>,
) {
    val stats: Stats get() = Stats(title, unit, period, description)

    val detail: Detail
        get() = Detail(
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

    data class Detail(
        val maxTransaction: Double,
        val timeSpan: QuoteTimeSpan,
        val rollingAverage: RollingAverage,
        val transactionsEntries: List<Entry>,
    )
}