package com.drowsynomad.manytomanyapp

import android.app.Application
import com.drowsynomad.manytomanyapp.di.dataModule
import com.drowsynomad.manytomanyapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

class ManyToManyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ManyToManyApplication)
            modules(presentationModule, dataModule)
        }
    }
}