package com.dieski.data.remote.di

import com.dieski.data.BuildConfig
import com.dieski.data.remote.network.adapter.NetworkResultCallAdapterFactory
import com.dieski.data.remote.network.monitor.NetworkMonitor
import com.dieski.data.remote.network.monitor.NetworkMonitorImpl
import com.dieski.data.remote.network.service.WeSkiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindsNetworkConnectivityManager(networkMonitorImpl: NetworkMonitorImpl): NetworkMonitor

    companion object {
        private const val BASE_URL: String = BuildConfig.BASE_URL
        private const val TAG = "NETWORK_LOG"

        @Provides
        @Singleton
        fun providesJsonBuilder(): Json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
            if (BuildConfig.DEBUG) prettyPrint = true
        }

        @Singleton
        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Timber.tag(TAG).d(message)
        }.apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        @Provides
        @Singleton
        @DefaultClient
        fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        @Provides
        @Singleton
        @DefaultApi
        fun provideRetrofit(
            @DefaultClient okHttpClient: OkHttpClient,
            json: Json
        ): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .build()

        @Provides
        @Singleton
        fun provideWeSkiApi(
            @DefaultApi retrofit: Retrofit
        ): WeSkiService {
            return retrofit.create(WeSkiService::class.java)
        }
    }
}