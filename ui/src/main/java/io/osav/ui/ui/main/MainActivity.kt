package io.osav.ui.ui.main

import android.os.Bundle
import io.osav.ui.R
import io.osav.ui.core.BaseActivity

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}