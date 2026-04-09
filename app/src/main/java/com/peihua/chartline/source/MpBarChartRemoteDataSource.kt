package com.peihua.chartline.source

import com.peihua.chartline.model.StatsItem
import com.peihua.chartline.model.StatsResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class MpBarChartRemoteDataSource @Inject constructor() {
    suspend fun transactions(
        count: Int, range: Int,
    ): StatsResponse {
        delay(2000)
        val values = ArrayList<StatsItem>()
        val start = 1f
        var i = start.toInt()
        while (i < start + count) {
            val value = (Math.random() * (range + 1)).toFloat()

            if (Math.random() * 100 < 25) {
                values.add(StatsItem(i.toLong(), value.toDouble()));
            } else {
                values.add(StatsItem(i.toLong(), value.toDouble()))
            }
            i++
        }
        return StatsResponse(
            description = "Transaction Rate",
            name = "transactions_per_second",
            period = "minute",
            status = "ok",
            unit = "transactions/second",
            values = values
        )
    }
}