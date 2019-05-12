package io.osav.ui.ui.main

import android.os.Bundle
import io.osav.domain.gateway.info.InfoGateway
import io.osav.ui.R
import io.osav.ui.core.BaseActivity
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class MainActivity: BaseActivity() {

    val g: InfoGateway by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        g.getInfo().subscribe()
    }
}