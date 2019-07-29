package com.nickspragg.licence

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.nickspragg.licence.model.LicenceData
import com.nickspragg.licence.provider.LicenceProvider
import junit.framework.Assert.*
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidLicenceProviderTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.nickspragg.licence.test", appContext.packageName)
    }

    @Test
    fun licenceProvider_returnsLicenceDataTypes() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        val provider = LicenceProvider(appContext)
        val output = provider.fetchLicences()
        assertThat(output.first(), instanceOf(LicenceData::class.java))
    }

}