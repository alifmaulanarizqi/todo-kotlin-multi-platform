package org.alifmaulanarizqi.todokmp

import android.app.Application
import org.alifmaulanarizqi.todokmp.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            config = {
                androidContext(this@MyApplication)
            }
        )
    }
}