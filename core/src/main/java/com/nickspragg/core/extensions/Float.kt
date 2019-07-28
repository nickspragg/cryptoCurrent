package com.nickspragg.core.extensions

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import java.text.DecimalFormat
import kotlin.math.roundToInt


fun Float.formatCurrency(currencySymbol: String = "$", rtl: Boolean = false): SpannableString {
    return SpannableString(this.formatCurrency(currencySymbol, rtl,false, true)).let {
        it.setSpan(
            RelativeSizeSpan(0.7f),
            if (rtl) it.length - 3 else it.length - 2,
            if (rtl) it.length - 1 else it.length, 0)
        it
    }
}


fun Float.formatCurrency(currencySymbol: String = "$", rtl: Boolean = false, roundCents: Boolean = false, displayCents: Boolean = true): String {
    val centsFormat = if(displayCents) ".00" else ".##"
    val format = if (rtl) "#,###,##0$centsFormat$currencySymbol" else "$currencySymbol#,###,##0$centsFormat"
    return DecimalFormat(format).format(
        this.let {
            if(roundCents) this.roundToInt() else this
        })
}


fun Float.formatAmount(roundCents: Boolean = false, displayCents: Boolean = true): String {
    val centsFormat = if(displayCents || (this % 1) != 0f) ".00" else ".##"
    val format = "#,###,##0$centsFormat"
    return DecimalFormat(format).format(
        this.let {
            if(roundCents) this.roundToInt() else this
        })
}


fun Float.roundTo(decimals: Int): Float {
    return DecimalFormat("#.".let { it.padEnd(it.length + decimals, '#') }).format(this).toFloat()
}