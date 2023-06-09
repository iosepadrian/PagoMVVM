package com.example.pagoappmvvm

import android.app.Application
import android.content.Context
import com.example.pagoappmvvm.di.repositoryModule
import com.example.pagoappmvvm.di.useCaseModule
import com.example.pagoappmvvm.di.utilsModule
import com.example.pagoappmvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PagoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@PagoApplication)
            androidLogger(level = Level.ERROR)
            modules(
                listOf(
                    repositoryModule,
                    utilsModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }

    companion object {
        @JvmStatic
        lateinit var appContext: Context
    }
}