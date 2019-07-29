package com.nickspragg.licence.provider

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nickspragg.licence.model.LicenceData
import org.apache.commons.io.IOUtils
import javax.inject.Inject

class LicenceProvider @Inject constructor(private val appContext: Context) {

    fun fetchLicences(): List<LicenceData> {
        val jsonString = IOUtils.toString(appContext.assets.open("licences.json"), "utf-8")
        val dataType = object : TypeToken<List<LicenceData>>() { }.getType()
        return Gson().fromJson(jsonString, dataType)
    }
}