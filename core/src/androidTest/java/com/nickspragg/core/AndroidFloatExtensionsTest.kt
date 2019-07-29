package com.nickspragg.core

import com.nickspragg.core.extensions.formatCurrency
import junit.framework.Assert.assertEquals
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class AndroidFloatExtensionsTest {

    @Test
    @Parameters("10000")
    fun floatToCurrencyTest(input: Float) {
        assertEquals(input.formatCurrency().toString(), "$10,000.00")
    }

}