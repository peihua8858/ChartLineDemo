package com.peihua.chartline.enums

enum class RollingAverage(val valueName: String, val value: String) {
    HOUR("1小时", "1hour"),
    FOUR_HOUR("4小时", "4hours"),
    EIGHT_HOURS("8小时", "8hours"),
    ONE_DAY("24小时", "24hours"),
    ONE_WEAK("1周", "1week"),
    FOUR_WEEKS("4周", "4weeks"),
}

enum class QuoteTimeSpan(val valueName: String, val value: String) {
    DAY("1天", "1days"),
    WEEK("7天", "7days"),
    MONTH("30天", "30days"),
    TWO_MONTHS("60天", "60days"),
    THREE_MONTHS("90天", "90days"),
    ONE_YEAR("365天", "365days"),
}