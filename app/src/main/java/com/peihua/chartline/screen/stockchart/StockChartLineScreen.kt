package com.peihua.chartline.screen.stockchart

import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.wangyiqian.stockchart.childchart.kchart.KChartConfig
import com.github.wangyiqian.stockchart.childchart.kchart.KChartFactory
import com.github.wangyiqian.stockchart.childchart.timebar.TimeBarConfig
import com.github.wangyiqian.stockchart.childchart.timebar.TimeBarFactory
import com.github.wangyiqian.stockchart.childchart.volumechart.VolumeChartConfig
import com.github.wangyiqian.stockchart.childchart.volumechart.VolumeChartFactory
import com.github.wangyiqian.stockchart.entities.IKEntity
import com.github.wangyiqian.stockchart.entities.KEntity
import com.peihua.chartline.component.StockChart
import com.peihua.chartline.model.MarketInformation
import com.peihua8858.compose.tools.rememberState
import kotlin.math.roundToLong
import com.peihua.chartline.component.random
import com.peihua.chartline.component.rangeTo
import com.peihua8858.tools.utils.dLog

@Composable
fun StockChartLineContent(
    modifier: Modifier = Modifier,
    data: MarketInformation,
) {
    var kConfig by rememberState(KChartConfig())
    val timeConfig by rememberState(TimeBarConfig())
    val volumeConfig by rememberState(VolumeChartConfig())
    StockChart(
        modifier = modifier.heightIn(max = 300.dp),
        updateConfig = { stockConfig ->
            stockConfig.apply {
                scaleAble = true
                // 最大缩放比例
                scaleFactorMax = 2f
                scrollSmoothly = false
                // 最小缩放比例
                scaleFactorMin = 0.5f
                // 网格线设置
                gridVerticalLineCount = 3
                gridHorizontalLineCount = 4
                backgroundColor = 0xff00001
                addChildCharts(
                    KChartFactory(this@StockChart, kConfig),
                    TimeBarFactory(this@StockChart, timeConfig),
                    VolumeChartFactory(this@StockChart, volumeConfig),

                )
                val entities = data.modelToLineData()
                setKEntities(entities, 0, entities.size - 1)
                notifyChanged()
            }
        },
    )
}

private fun MarketInformation.modelToLineData(): List<IKEntity> {
    val transactionsEntries = this.chartEntries
    dLog { "modelToLineData>>>chartEntries.size:${transactionsEntries.size}" }
    val values = mutableListOf<KEntity>()
    for (i in 1f..100f) {
        println(i)
    }
//    val labels = mutableListOf<String>()
    transactionsEntries.forEach {
//        val time = it.x.format("HH:mm:ss")
//        labels.add(time)
        val lowRandom = (0.1f..1f).random()
        val heightRandom = (1f..1.5f).random()
        values.add(
            KEntity(
                (it.y * heightRandom),
                (it.y * lowRandom),
                (it.y * heightRandom),
                (it.y * lowRandom), (it.y * (10..100).random()).roundToLong(), (it.x*1000).toLong()
            ).apply { dLog { "modelToLineData>>>KEntity:$this" } }
        )
    }

    return values
}