package com.nickspragg.core.extensions

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import java.text.DecimalFormat
import kotlin.math.roundToInt

/**
 * Formats the double into a standard currency format using two decimal places (#,###,##0.00) with the option
 * to specify a currency symbol and ltr or rtl layout.
 * @param currencySymbol String: The currency symbol to be used (default: $).
 * @param rtl Boolean: True - Appends the currency symbol on the right.
 *            False (default) - Appends the currency symbol on the left.
 * @return SpannableString: The formatted currency.
 */
fun Float.formatCurrency(currencySymbol: String = "$", rtl: Boolean = false): SpannableString {
    return SpannableString(this.formatCurrency(currencySymbol, rtl,false, true)).let {
        it.setSpan(
            RelativeSizeSpan(0.7f),
            if (rtl) it.length - 3 else it.length - 2,
            if (rtl) it.length - 1 else it.length, 0)
        it
    }
}

/**
 * Formats the double into a string using standard currency format.
 * @param currencySymbol String: The currency symbol to be used (default: $).
 * @param rtl Boolean: True - Appends the currency symbol on the right.
 *            False (default) - Appends the currency symbol on the left.
 * @param roundCents: Dictates whether or not to round the value to an integer before formatting the currency.
 * @param displayCents: Dictates whether or not cents will always be displayed in the formatted currency.
 *            True (default) - Cents will always be included with a trailing zeros.
 *            False - Cents will only be included if required.
 */
fun Float.formatCurrency(currencySymbol: String = "$", rtl: Boolean = false, roundCents: Boolean = false, displayCents: Boolean = true): String {
    val centsFormat = if(displayCents) ".00" else ".##"
    val format = if (rtl) "#,###,##0$centsFormat$currencySymbol" else "$currencySymbol#,###,##0$centsFormat"
    return DecimalFormat(format).format(
        this.let {
            if(roundCents) this.roundToInt() else this
        })
}

/**
 * Formats the double with the option to specify whether or not using
 * two decimal places(#,###,##0.00).
 * @param roundCents: Dictates whether or not to round the value to an integer before formatting the currency.
 * @param displayCents: Dictates whether or not cents will always be displayed in the formatted currency.
 *            True (default) - Cents will always be included with a trailing zeros.
 *            False - Cents will only be included with a trailing zeros when the value has cent(s).
 * @return String: The formatted currency.
 */
fun Float.formatAmount(roundCents: Boolean = false, displayCents: Boolean = true): String {
    val centsFormat = if(displayCents || (this % 1) != 0f) ".00" else ".##"
    val format = "#,###,##0$centsFormat"
    return DecimalFormat(format).format(
        this.let {
            if(roundCents) this.roundToInt() else this
        })
}

/**
 * Rounds the double to a specific number of decimal places.
 * @param decimals: The number of decimal places to retain when rounding.
 */
fun Float.roundTo(decimals: Int): Float {
    return DecimalFormat("#.".let { it.padEnd(it.length + decimals, '#') }).format(this).toFloat()
}