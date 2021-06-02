package com.arrkariz.capstoneprojectmade

import android.app.Application
import com.arrkariz.capstoneprojectmade.di.useCaseModule
import com.arrkariz.capstoneprojectmade.di.viewModelModule
import com.arrkariz.core.di.databaseModule
import com.arrkariz.core.di.networkModule
import com.arrkariz.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}