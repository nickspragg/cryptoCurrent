package com.nickspragg.core

import com.nickspragg.core.extensions.asDaysToEpoch
import junit.framework.Assert.assertEquals
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class FloatExtensionsTest {

    @Test
    @Parameters("1")
    fun floatAsDayToEpochTest(input: Float) {
        assertEquals(input.asDaysToEpoch(), 86400f)
    }

}