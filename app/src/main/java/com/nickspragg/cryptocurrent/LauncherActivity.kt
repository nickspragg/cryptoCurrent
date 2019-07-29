package com.nickspragg.cryptocurrent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nickspragg.currentmarket.CurrentMarketActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, CurrentMarketActivity::class.java))
        finish()
    }
}
