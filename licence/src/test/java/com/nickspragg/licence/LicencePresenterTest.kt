package com.nickspragg.licence

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.nickspragg.licence.provider.LicenceProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LicencePresenterTest {

    private lateinit var presenter: LicencePresenter
    @Mock
    private lateinit var licenceProvider: LicenceProvider
    @Mock
    private lateinit var view: LicenceContract.View

    @Before
    fun setup() {
        presenter = LicencePresenter(view, licenceProvider)
    }

    @Test
    fun initTest() {
        whenever(licenceProvider.fetchLicences()).thenReturn(emptyList())
        presenter.init()

        verify(view).showLicences(anyList())
        verifyNoMoreInteractions(view)
    }
}