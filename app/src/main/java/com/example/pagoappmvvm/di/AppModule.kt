package com.example.pagoappmvvm.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.pagoappmvvm.network.ApiServiceProvider
import com.example.pagoappmvvm.network.NetworkExceptionHandler
import com.example.pagoappmvvm.network.RemoteServicesHandler
import com.example.pagoappmvvm.repository.ContactRepository
import com.example.pagoappmvvm.repository.SessionManager
import com.example.pagoappmvvm.ui.contacts.ContactsViewModel
import com.example.pagoappmvvm.usecase.GetContactsUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { ContactsViewModel(get(), get()) }
}

val repositoryModule = module {
    single { provideSharedPreferences(androidContext()) }
    single { SessionManager(get()) }
    single { ContactRepository(get(), get()) }
}

val utilsModule = module {
    single { provideOkHttpBuilder() }
    single { ApiServiceProvider(provideOkHttpBuilder().build()).createApiService() }
    single { provideMoshi() }
    single { NetworkExceptionHandler(get()) }
    single { RemoteServicesHandler(get()) }
}

val useCaseModule = module {
    single { GetContactsUseCase(get(), get()) }
}

fun provideSharedPreferences(context: Context): SharedPreferences {
    return EncryptedSharedPreferences.create(
        "AppPreferences",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun provideOkHttpBuilder(): OkHttpClient.Builder {
    val okHttpClientBuilder = OkHttpClient.Builder()

    okHttpClientBuilder
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)

    return okHttpClientBuilder
}
