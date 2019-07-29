package com.nickspragg.core.extensions

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import java.text.DecimalFormat

/**
 * Formats the float into a standard currency format using two decimal places (#,###,##0.00) with the option
 * to specify a currency symbol layout.
 * @param currencySymbol String: The currency symbol to be used (default: $).
 * @return SpannableString: The formatted currency.
 */
fun Float.formatCurrency(currencySymbol: String = "$"): SpannableString {
    val format = "$currencySymbol#,###,##0.00"
    return SpannableString(DecimalFormat(format).format(this)).let {
        it.setSpan(
            RelativeSizeSpan(0.7f),
            it.length - 2,
            it.length, 0
        )
        it
    }
}


/**
 * Convert a float representing a number of days into Epoch unit.
 * @return Float: Epoch representation of the number of days.
 */
fun Float.asDaysToEpoch(): Float {
    return this.times(86400)
}