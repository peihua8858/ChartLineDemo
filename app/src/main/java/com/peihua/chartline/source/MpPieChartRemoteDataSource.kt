package com.peihua.chartline.source

import com.peihua.chartline.model.StatsItem
import com.peihua.chartline.model.StatsResponse
import com.peihua.chartline.model.ValuesItem
import kotlinx.coroutines.delay
import javax.inject.Inject

class MpPieChartRemoteDataSource @Inject constructor() {
    val parties: Array<String> = arrayOf(
        "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
        "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
        "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
        "Party Y", "Party Z"
    )

    suspend fun transactions(
        count: Int, range: Int,
    ): StatsResponse {
        delay(2000)
        val values = ArrayList<ValuesItem>()
        val start = 1f
        var i = start.toInt()
        while (i < start + count) {
            val value = (Math.random() * range) + range / 5f
            if (Math.random() * 100 < 25) {
                values.add(ValuesItem(value, parties[i % parties.size]));
            } else {
                values.add(ValuesItem(value, parties[i % parties.size]))
            }
            i++
        }
        return StatsResponse(
            description = "Transaction Rate",
            name = "transactions_per_second",
            period = "minute",
            status = "ok",
            unit = "transactions/second",
            labels = values
        )
    }
}